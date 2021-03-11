package com.baruch.coupons.dto.coupon.datapresentation;

import java.util.Date;

import com.baruch.coupons.dto.dataInterfaces.ICouponDataObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CouponBasicData implements ICouponDataObject {
	
	//VARIABLES
	
	private String couponTitle, companyName;
	
	private Date endDate;
	
	private float price;
	
	private long id;
	
}
