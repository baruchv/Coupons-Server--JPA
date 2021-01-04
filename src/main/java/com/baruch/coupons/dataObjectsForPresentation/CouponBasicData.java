package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

public class CouponBasicData implements ICouponDataObject {
	
	//VARIABLES
	
	private String couponTitle, companyName;
	
	private Date endDate;
	
	private float price;
	
	private long id;
	
	//CTORS
	
	public CouponBasicData(Coupon coupon) throws ApplicationException{
		try {
			this.id = coupon.getId();
			this.price = coupon.getPrice();
			this.endDate = coupon.getEndDate();
			this.couponTitle = coupon.getTitle();
			this.companyName = coupon.getCompany().getName();
		} catch (Exception e) {
			throw new ApplicationException("new CouponDataBasic(Coupon) failed for " + coupon, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	//METHODS
	
	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
