	package com.baruch.coupons.logic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.dto.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.dto.coupon.CouponAmountAndTime;
import com.baruch.coupons.dto.purchase.PurchaseDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserTypes;
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
	 */
	@Transactional
	public long addPurchase(PurchaseDto purchaseDto, UserLoginData userDetails) throws ApplicationException{
		int purchaseAmount = purchaseDto.getAmount();
		long couponID = purchaseDto.getCouponID();
		long userID = userDetails.getId();
		validateAddPurchase(purchaseAmount, couponID, userID);
		Date now = new Date(System.currentTimeMillis());
		purchaseDto.setTimeStamp(now);
		Purchase purchase = generateEntity(purchaseDto, userID);
		try {
			repository.save(purchase);
			couponsController.decreseFromCouponAmount(purchase.getAmount(), couponID);
			return purchase.getId();
		}
		catch(Exception e) {
			throw new ApplicationException("repository.addPurchase() failed for  = " + purchase, ErrorTypes.GENERAL_ERROR,e);
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

	//This method doesn't serve Company users.

	public IPurchaseDataObject getPurchase(long purchaseID, UserLoginData userDetails) throws ApplicationException {
		UserTypes userType = userDetails.getType();
		try {
			if(userType.equals(UserTypes.ADMIN)){
				return repository.getPurchaseForAdmin(purchaseID);
			}
			return repository.getPurchaseForCustomer(purchaseID);
		} catch (Exception e) {
			throw new ApplicationException("repository.getPurchase() failed for purchaseID = " + purchaseID,
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


	private void validateAddPurchase(int amount, long couponID, long userID) throws ApplicationException{
		if(amount < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_ERROR);
		}
		CouponAmountAndTime couponDetails =  couponsController.getCouponAmountAndTime(couponID);
		if(couponDetails == null){
			throw new ApplicationException("ERROR: validateUpdateCoupon() failed for couponID: " + couponID +" userID: " + userID, ErrorTypes.NO_COUPON_FOUND);
		}
		if( couponDetails.getAmount() < amount) {
			throw new ApplicationException(ErrorTypes.OUT_OF_STOCK_ERROR);
		}
		
		//Dates validation are needed for 2 reasons:
		//1) Timer task failure.
		//2) Cleint bypass.
		Calendar now = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(couponDetails.getStartDate());
		if(now.before(startDate)){
			throw new ApplicationException("addPurchase failed for couponID: " + couponID, ErrorTypes.INVALID_COUPON_ERROR);
		}
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(couponDetails.getEndDate());
		if(now.after(endDate)){
			throw new ApplicationException("addPurchase failed for couponID: " + couponID, ErrorTypes.INVALID_COUPON_ERROR);
		}
	}
	
	private Purchase generateEntity(PurchaseDto purchaseDto, long userID) throws ApplicationException{
		Coupon coupon = couponsController.getCouponEntity(purchaseDto.getCouponID());
		User user = usersController.getUserEntity(userID);
		return new Purchase(purchaseDto,coupon,user);
	}

}
