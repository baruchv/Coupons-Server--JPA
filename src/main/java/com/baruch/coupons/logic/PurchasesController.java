	package com.baruch.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.dto.PurchaseDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.repository.IPurchasesRepository;

@Controller
public class PurchasesController {

	@Autowired
	IPurchasesRepository repository;

	@Autowired
	CouponsController couponsController;

	@Autowired
	UsersController usersController;
	
	@Autowired
	CompaniesController companiesController;

	//PUBLIC-METHODS


	/* Adding a new purchase.
	 * The amount of the purchased coupon will be changed accordingly.
	 * The method assumes all coupons in the DB are valid.
	 */
	public long addPurchase(PurchaseDto purchaseDto, UserLoginData userDetails) throws ApplicationException{
		validateAddPurchase(purchaseDto.getAmount(), purchaseDto.getCouponID());
		purchaseDto.setUserID(userDetails.getId());
		Purchase purchase = generateEntity(purchaseDto);
		try {
			repository.save(purchase);
			CouponDto couponDto = new CouponDto(purchase.getCoupon());
			int newAmount = couponDto.getAmount() - purchaseDto.getAmount();
			couponDto.setAmount(newAmount);
			couponsController.updateCoupon(couponDto, userDetails);
			return purchase.getId();
		}
		catch(Exception e) {
			throw new ApplicationException("repository.addPurchase() failed for  = " + purchaseDto, ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public void deletePurchase(long purchaseID) throws ApplicationException{
		try {
			repository.deleteById(purchaseID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.deleteById() failed for purchaseID = " + purchaseID, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	
	/*
	 * This method serves all user-types and will return each user-type a List of customed data objects.
	 */
	public List<IPurchaseDataObject> getAllPurchases(UserLoginData userDetails) throws ApplicationException{
		try {
			switch (userDetails.getType()) {
			case CUSTOMER:
				long userID = userDetails.getId();
				return repository.getAllPurchasesForCustomer(userID);
			case COMPANY:
				long companyID = userDetails.getCompanyID();
				return repository.getAllPurchasesForCompany(companyID);
			default:
				return repository.getAllPurchases();
			}
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getAllPurchases() failed ", ErrorTypes.GENERAL_ERROR,e);
		}
	}

	/*
	 * This method serves Admin users
	 */
	public IPurchaseDataObject getPurchase(long purchaseID) throws ApplicationException {
		try {
			return repository.getPurchase(purchaseID);
		} catch (Exception e) {
			throw new ApplicationException("repository.deleteById() failed for purchaseID = " + purchaseID,
					ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	/*
	 * This method serves Admin users only
	 */
	public List<IPurchaseDataObject> getPurchasesByUserID(long userID) throws ApplicationException{
		try {
			return repository.getPurchasesByUserID(userID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getPurchaseByUserID() failed for userID = " + userID, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	/*
	 * This method serves Admin users only
	 */
	public List<IPurchaseDataObject> getPurchasesByCompanyID(long companyID) throws ApplicationException{
		try {
			return repository.getPurchasesByCopmany(companyID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getPurchasesByCompanyID() failed for companyID = " + companyID, ErrorTypes.GENERAL_ERROR,e);
		}
	}


	//PRIVATE-METHODS

	/*
	 * An assumption - all coupons in the DB are valid, thanks to the CouponsValidator class.
	 * In case there is no coupon associated with couponID, couponsController.getCoupon(couponID) will throw an ApplicationException.
	 */
	private void validateAddPurchase(int amount, long couponID) throws ApplicationException{
		if(amount < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_ERROR);
		}
		int couponAmount =  couponsController.getCouponsAmount(couponID);
		if(couponAmount < amount) {
			throw new ApplicationException(ErrorTypes.OUT_OF_STOCK_ERROR);
		}
	}
	
	private Purchase generateEntity(PurchaseDto purchaseDto) throws ApplicationException{
		Coupon coupon = couponsController.getCouponEntity(purchaseDto.getCouponID());
		User user = usersController.getUserEntity(purchaseDto.getUserID());
		return new Purchase(purchaseDto,coupon,user);
	}

	

}
