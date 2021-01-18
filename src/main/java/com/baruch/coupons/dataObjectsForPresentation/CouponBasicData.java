package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
}
