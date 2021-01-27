package com.baruch.coupons.logic;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dto.CouponAmountAndTime;
import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.Categories;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.repository.ICouponRespository;
import com.baruch.coupons.timertasks.CouponsValidator;

@Controller
public class CouponsController {
	
	@Autowired
	private ICouponRespository repository;
	
	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private CouponsValidator timerTask;
	
	//PUBLIC-METHODS
	
	public long createCoupon(CouponDto couponDto, UserLoginData userDetails) throws ApplicationException{
		long companyID = userDetails.getCompanyID();
		validateCreateCoupon(couponDto, companyID, Calendar.getInstance());
		Company company = companiesController.getCompanyEntity(companyID);
		Coupon coupon = new Coupon(couponDto, company);
		try {
			repository.save(coupon);
			return coupon.getId();
		} catch (Exception e) {
			throw new ApplicationException("createCoupon() failed for " + couponDto + " " + userDetails,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	@Transactional
	public void updateCoupon(CouponDto couponDto, long couponID, UserLoginData userDetails) throws ApplicationException{
		validateUpdateCoupon(couponDto, couponID, userDetails.getCompanyID());
		int amount = couponDto.getAmount();
		float price = couponDto.getPrice();
		String image = couponDto.getImage();
		try {
			repository.updateCoupon(amount, price, image, couponID);
		} catch (Exception e) {
			throw new ApplicationException("updateCoupon() failed for " + couponDto,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	public void deleteCoupon(long couponID) throws ApplicationException{
		try {
			repository.deleteById(couponID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.deleteCoupon() failed for id = " + couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public ICouponDataObject getCoupon(long couponID, UserLoginData userDetails) throws ApplicationException{
		UserTypes type = userDetails.getType();
		try {
			if(type.equals(UserTypes.ADMIN)){
				return repository.getCouponForAdmin(couponID);
			}
			return repository.getCouponDefault(couponID);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCoupon() failed for couponID = " + couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	//Customers get only valid coupons.	
	public List<ICouponDataObject> getAllCoupons(UserLoginData userDetails) throws ApplicationException{
		try {
			UserTypes type = userDetails.getType();
			if(type.equals(UserTypes.CUSTOMER)){
				return repository.getAllCouponsForCustomer(new Date(System.currentTimeMillis()));
			}
			return repository.getAllCoupons();	
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getAllCoupons() failed", ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public List<ICouponDataObject> getCouponsByCategory(Categories category) throws ApplicationException{
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

	@PostConstruct
	private void validateCoupons(){
		Timer timer = new Timer();
		long dayInMilliseconds = 1000*60*60*24;
		timer.scheduleAtFixedRate(timerTask, 0, dayInMilliseconds);
	}

	@Transactional
	void decreseFromCouponAmount(int amount, long couponID){
		try {
			repository.decreseFromCouponAmount(amount, couponID);
		} catch (Exception e) {
			ApplicationException appException = new ApplicationException("decreseFromCouponAmount() failed for amount: " + amount + " couponID: " + couponID, ErrorTypes.GENERAL_ERROR,e);
			appException.printStackTrace();
		}
	}
	
	CouponAmountAndTime getCouponAmountAndTime(long couponID) throws ApplicationException{
		try {
			return repository.getCouponAmountAndTime(couponID);
		} catch (Exception e) {
			throw new ApplicationException("getBasicCoupon() failed for couponID = " + couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	 Coupon getCouponEntity(long couponID) throws ApplicationException{
		try {
			return repository.findById(couponID).get();
		}
		catch(Exception e){
			throw new ApplicationException("repository.findById() failed for couponID = "+ couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	//This validation may indicate a cleint bypass, therefore the user should be tracked.
	 void validateCouponID(long couponID, long companyID) throws ApplicationException{
		Company company = companiesController.getCompanyEntity(companyID);
		if( ! repository.existsByCompanyAndId(company, couponID)) {
			throw new ApplicationException("ERROR: validateCouponID failed for couponID: " + couponID +" companyID: " + companyID ,ErrorTypes.NO_COUPON_FOUND);
		}
	}
	
	private void validateCreateCoupon(CouponDto couponDto, long companyID, Calendar now) throws ApplicationException{
		validateTitle(couponDto.getTitle(),companyID);
		validateDescription(couponDto.getDescription());
		validateDates(couponDto,now);
		if(couponDto.getAmount() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_ERROR);
		}
		if(couponDto.getPrice() < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE_ERROR);
		}
	}
	
	// This validation may indicate a cleint bypass, therefore the user should be tracked.
	private void validateUpdateCoupon(CouponDto couponDto, long couponID, long userID) throws ApplicationException{
		validateCouponID(couponID, userID);
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
		try {
			if (repository.existsByCompanyAndTitle(company, title)) {
				throw new ApplicationException(ErrorTypes.DUPLICATE_TITLE_ERROR);
			}
		} catch (Exception e) {
			if(e instanceof ApplicationException){
				throw e;
			}
			throw new ApplicationException("validateTitle() failed for title: " + title + " companyID: " +
			 companyID, ErrorTypes.GENERAL_ERROR, e);
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
		//In case couponDto.startDate is null, we would like to initialise the new coupon's
		//startDate property to the current time.
		Calendar startDate = Calendar.getInstance();
		if(couponDto.getStartDate() != null){
			startDate.setTimeInMillis(couponDto.getStartDate().getTime());
		}
		//As product conception - the dates should be rounded down.  
		roundByHours(startDate);
		roundByHours(now);
		if (startDate.before(now)) {
			throw new ApplicationException(ErrorTypes.INVALID_STARTDATE_ERROR);
		}
		Calendar endDate = Calendar.getInstance();
		endDate.setTimeInMillis(couponDto.getEndDate().getTime());
		roundByHours(endDate);
		if(endDate.before(now)){
			throw new ApplicationException(ErrorTypes.INVALID_DATES_ERROR);
		}
	}

	private void roundByHours(Calendar time){
		time.set(Calendar.MILLISECOND, 0);
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
	}
	
	
	
}
