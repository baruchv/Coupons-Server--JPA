package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseDataForCustomer implements IPurchaseDataObject{
	
	//VARIABLES
	
	private int   amount;
	
	private Date  timeStamp;

	private float totalPrice;

	private String couponTitle, companyName;
	
	//CTORS
	
	/*
	 * Due to Lazy FetchType, the methods getCoupon(), getCompany() , are DB queries.
	 * Therefore, they might invoke exceptions.
	 */
	public PurchaseDataForCustomer(Purchase purchase) throws ApplicationException{
		try {
			this.amount = purchase.getAmount();
			this.timeStamp = purchase.getTimeStamp();
			Coupon coupon = purchase.getCoupon();
			this.couponTitle = coupon.getTitle();
			float price = coupon.getPrice();
			this.totalPrice = this.amount * price;
			Company company = coupon.getCompany();
			this.companyName = company.getName();
		} catch (Exception e) {
			throw new ApplicationException("new PurchaseDataforCustomer(Purchase) failed for " + purchase, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
}
