package com.baruch.coupons.dto;

import java.sql.Date;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PurchaseDto  {
	
	//VARIABLES
	
	private int   amount;
	
	private long  id, userID, couponID;
	
	private Date  timeStamp;

	private float totalPrice;

	private String couponTitle, companyName;
	
	//CTORS
	
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
	
}
