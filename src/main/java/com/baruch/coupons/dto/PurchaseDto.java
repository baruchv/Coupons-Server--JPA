package com.baruch.coupons.dto;

import java.sql.Date;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

/*
 * The purpose of this DTO is receiving Purchase-related data from users:
 */
public class PurchaseDto  {
	
	//VARIABLES
	
	private int   amount;
	
	private long  id, userID, couponID;
	
	private Date  timeStamp;

	private float totalPrice;

	private String couponTitle, companyName;
	
	//CTORS
	
	public PurchaseDto() {
		super();
	}
	
	/*
	 * Due to Lazy fetchType, the methods getCoupon(), getUser() , are DB queries.
	 * Therefore, they might invoke exceptions.
	 */
	public PurchaseDto(Purchase purchase) throws ApplicationException{
		try {
			this.id = purchase.getId();
			this.amount = purchase.getAmount();
			this.timeStamp = purchase.getTimeStamp();
			this.userID = purchase.getUser().getId();
			Coupon coupon = purchase.getCoupon();
			this.couponID = coupon.getId();
			this.couponTitle = coupon.getTitle();
			this.totalPrice = coupon.getPrice() * amount;
			Company company = coupon.getCompany();
			this.companyName = company.getName();
		}
		catch(Exception e) {
			throw new ApplicationException("new PurchaseDto(Purchase) failed for " + purchase, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	//METHODS
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public long getCouponID() {
		return couponID;
	}
	
	public void setCouponID(long couponID) {
		this.couponID = couponID;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "PurchaseDto [amount=" + amount + ", id=" + id + ", userID=" + userID + ", couponID=" + couponID
				+ ", timeStamp=" + timeStamp + ", totalPrice=" + totalPrice + ", couponTitle=" + couponTitle
				+ ", companyName=" + companyName + "]";
	}
	
	
}
