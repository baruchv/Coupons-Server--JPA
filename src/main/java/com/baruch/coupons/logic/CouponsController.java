package com.baruch.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.Category;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.repository.ICouponRespository;

@Controller
public class CouponsController {
	
	@Autowired
	private ICouponRespository repository;
	
	@Autowired
	private CompaniesController companiesController;
	
	//PUBLIC-METHODS
	
	public long createCoupon(CouponDto couponDto, UserLoginData userDetails) throws ApplicationException{
		long companyID = userDetails.getCompanyID();
		couponDto.setCompanyID(companyID);
		validateCreateCoupon(couponDto, Calendar.getInstance());
		Coupon coupon = generateEntity(couponDto);
		repository.save(coupon);
		return coupon.getId();
	}
	
	@Transactional
	public void updateCoupon(CouponDto couponDto, UserLoginData userDetails) throws ApplicationException{
		couponDto.setCompanyID(userDetails.getCompanyID());
		validateUpdateCoupon(couponDto);
		int amount = couponDto.getAmount();
		float price = couponDto.getPrice();
		String image = couponDto.getImage();
		long id = couponDto.getId();
		repository.updateCoupon(amount, price, image, id);
	}
	
	public void deleteCoupon(long id) throws ApplicationException{
		try {
			repository.deleteById(id);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.deletCoupon() failed for id = " +id, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public ICouponDataObject getCoupon(long id) throws ApplicationException{
		try {
			return repository.getCoupon(id);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCoupon() failed for id = "+id, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public List<ICouponDataObject> getAllCoupons() throws ApplicationException{
		try {
			return repository.getAllCoupons();
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getAllCoupons() failed", ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public List<ICouponDataObject> getCouponsByCategory(Category category) throws ApplicationException{
		try {
			return repository.getCouponsByCategory(category);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCouponsByCategory() failed fot category = "+ category, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public List<ICouponDataObject> getCouponsByCompany(long companyID) throws ApplicationException{
		try {
			return repository.getCouponsByCompany(companyID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCouponsByCompany() failed fot companyID = " +companyID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public List<ICouponDataObject> getCouponsByMaxPrice(float price, UserLoginData userDetails) throws ApplicationException{
		long userID = userDetails.getId();
		try {
			return repository.getPurchasedCouponsByMaxPrice(userID, price);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCouponsByMaxPrice() failed for userID = " +userID +", maxPrice = " + price, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	
	//PRIVATE-METHODS
	
	protected int getCouponsAmount(long id) throws ApplicationException{
		try {
			return repository.getCouponsAmount(id);
		} catch (Exception e) {
			throw new ApplicationException("getBasicCoupon() failed for couponID = " +id, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	protected Coupon getCouponEntity(long couponID) throws ApplicationException{
		try {
			return repository.findById(couponID).get();
		}
		catch(Exception e){
			throw new ApplicationException("repository.findById() failed for couponID = "+ couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	protected void validateCouponID(long id) throws ApplicationException{
		if( ! repository.existsById(id)) {
			throw new ApplicationException("CouponsController.validateCouponID failed for ID: " + id, ErrorTypes.GENERAL_ERROR);
		}
	}
	
	private void validateCreateCoupon(CouponDto couponDto,Calendar now) throws ApplicationException{
		validateTitle(couponDto.getTitle(),couponDto.getCompanyID());
		validateDescription(couponDto.getDescription());
		validateDates(couponDto,now);
		if(couponDto.getAmount() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_ERROR);
		}
		if(couponDto.getPrice() < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE_ERROR);
		}
	}
	
	private void validateUpdateCoupon(CouponDto couponDto) throws ApplicationException{
		validateCouponID(couponDto.getId());
		if(couponDto.getAmount() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_ERROR);
		}
		if(couponDto.getPrice() < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE_ERROR);
		}
	}
	
	private void validateTitle(String title,long companyID) throws ApplicationException{
		if(title == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_TITLE_ERROR);
		}
		if(title.length()<2) {
			throw new ApplicationException(ErrorTypes.INVALID_TITLE_ERROR);
		}
		Company company = companiesController.getCompanyEntity(companyID);
		if(repository.existsByCompanyAndTitle(company, title)) {
			throw new ApplicationException(ErrorTypes.DUPLICATE_TITLE_ERROR);
		}
	}
	
	private void validateDescription(String description) throws ApplicationException{
		if(description == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_DESCRIPTION_ERROR);
		}
		if(description.length()<2) {
			throw new ApplicationException(ErrorTypes.INVALID_DESCRIPTION_ERROR);
		}
	}
	
	private void validateDates(CouponDto couponDto,Calendar now) throws ApplicationException{
		if (couponDto.getEndDate() == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_ENDDATE_ERROR);
		}
		Calendar startDate = Calendar.getInstance();
		if(couponDto.getStartDate() != null){
			startDate.setTimeInMillis(couponDto.getStartDate().getTime());
			if(isBefore(startDate,now)) {
			throw new ApplicationException(ErrorTypes.INVALID_STARTDATE_ERROR);
			}
		}
		Calendar endDate = Calendar.getInstance();
		endDate.setTimeInMillis(couponDto.getEndDate().getTime());;
		if(isBefore(endDate,startDate)) {
			throw new ApplicationException(ErrorTypes.INVALID_DATES_ERROR);
		}
	}
	
	private boolean isBefore(Calendar time1, Calendar time2) {
		if(time1.get(Calendar.YEAR) < time2.get(Calendar.YEAR)) {
			return true;
		}
		else if(time1.get(Calendar.YEAR) == time2.get(Calendar.YEAR)) {
			if(time1.get(Calendar.MONTH) < time2.get(Calendar.MONTH)) {
				return true;
			}
			else if(time1.get(Calendar.MONTH) == time2.get(Calendar.MONTH)) {
				if(time1.get(Calendar.DAY_OF_MONTH) < time2.get(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}	
		}
		
		return false;
	}
	
	private Coupon generateEntity(CouponDto couponDto) throws ApplicationException {
		Company company = companiesController.getCompanyEntity(couponDto.getCompanyID());
		Coupon coupon = new Coupon(couponDto);
		coupon.setCompany(company);
		return coupon;
	}

}
