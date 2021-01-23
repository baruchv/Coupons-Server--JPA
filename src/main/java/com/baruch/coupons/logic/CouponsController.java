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
import com.baruch.coupons.entities.User;
import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dataObjectsForPresentation.CouponDataForCustomer;
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
	private UsersController usersController;

	@Autowired
	private CouponsValidator timerTask;
	
	//PUBLIC-METHODS
	
	public long createCoupon(CouponDto couponDto, UserLoginData userDetails) throws ApplicationException{
		validateCreateCoupon(couponDto, Calendar.getInstance());
		long companyID = userDetails.getCompanyID();
		try {
			Company company = companiesController.getCompanyEntity(companyID);
			Coupon coupon = new Coupon(couponDto, company);
			repository.save(coupon);
			return coupon.getId();
		} catch (Exception e) {
			throw new ApplicationException("createCoupon() failed for " + couponDto + " " + userDetails,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	@Transactional
	public void updateCoupon(CouponDto couponDto, UserLoginData userDetails) throws ApplicationException{
		couponDto.setCompanyID(userDetails.getCompanyID());
		validateUpdateCoupon(couponDto);
		int amount = couponDto.getAmount();
		float price = couponDto.getPrice();
		String image = couponDto.getImage();
		long id = couponDto.getId();
		try {
			repository.updateCoupon(amount, price, image, id);
		} catch (Exception e) {
			throw new ApplicationException("updateCoupon() failed for " + couponDto,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	public void markAsfavorite(long couponID, UserLoginData userDetails) throws ApplicationException{
	    validateCouponID(couponID);
		try {
			Coupon coupon = repository.findById(couponID).get();
			long userID = userDetails.getId();
			User user = usersController.getUserEntity(userID);
			coupon.getUsers().add(user);
			user.getFavorites().add(coupon);
			repository.save(coupon);
			usersController.save(user);
		} catch (Exception e) {
			throw new ApplicationException("markAsfavorite() failed for " + couponID +", " + userDetails, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	public void deleteFromfavorites(long couponID, UserLoginData userDetails) throws ApplicationException{
		validateCouponID(couponID);
		try {
			Coupon coupon = repository.findById(couponID).get();
			long userID = userDetails.getId();
			User user = usersController.getUserEntity(userID);
			if( coupon.getUsers() == null || user.getFavorites() == null) {
				return;
			}
			coupon.getUsers().remove(user);
			user.getFavorites().remove(coupon);
			repository.save(coupon);
			usersController.save(user);
		} catch (Exception e) {
			throw new ApplicationException("deleteFromfavorite() failed for " + couponID +", " + userDetails, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	public void deleteCoupon(long couponID) throws ApplicationException{
		try {
			Coupon coupon = repository.findById(couponID).get();
			usersController.deleteFromAllUsersfavorites(coupon);
			repository.delete(coupon);
		}
		catch(Exception e) {
			throw new ApplicationException("repository.deleteCoupon() failed for id = " + couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	public ICouponDataObject getCoupon(long couponID, UserLoginData userDetails) throws ApplicationException{
		UserTypes type = userDetails.getType();
		try {
			switch (type) {
			case CUSTOMER:
				long userID = userDetails.getId();
				User user = usersController.getUserEntity(userID);
				CouponDataForCustomer coupon = (CouponDataForCustomer) repository.getCouponForCustomer(couponID);
				if(repository.isFavorite(user, couponID)){
					coupon.setIsfavorite(true);
				}
				return coupon;
			case COMPANY:
				return repository.getCouponForCompany(couponID);
			default:
				return repository.getCouponForAdmin(couponID);
			}
		}
		catch(Exception e) {
			throw new ApplicationException("repository.getCoupon() failed for couponID = " + couponID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
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
	
	public List<ICouponDataObject> getAllfavorites(UserLoginData userDetails) throws ApplicationException{
		
		long userID = userDetails.getId();

		try {
			User user = usersController.getUserEntity(userID);
			return repository.getAllfavorites(user);
		} 
		catch (Exception e) {
			throw new ApplicationException("getAllfavorites() failed for userID = " +userID, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	
	//PRIVATE-METHODS

	@PostConstruct
	private void validateCoupons(){
		Timer timer = new Timer();
		Calendar firstTime = Calendar.getInstance();
		firstTime.add(Calendar.DAY_OF_MONTH, 1);
		firstTime.set(Calendar.HOUR_OF_DAY, 0);
		long dayInMilliseconds = 1000*60*60*24;
		timer.scheduleAtFixedRate(timerTask, firstTime.getTimeInMillis(), dayInMilliseconds);
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
	
	 void validateCouponID(long couponID) throws ApplicationException{
		if( ! repository.existsById(couponID)) {
			throw new ApplicationException(ErrorTypes.NO_COUPON_ID);
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
	
	
	
}
