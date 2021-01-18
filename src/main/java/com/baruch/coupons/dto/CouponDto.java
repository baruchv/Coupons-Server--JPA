package com.baruch.coupons.dto;


import java.sql.Date;

import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@NoArgsConstructor
public class CouponDto {
	
	//VARIABLES
	
	private int amount;
	
	private long  id, companyID;
	
	private float  price;
	
	private String  title, description, image;
	
	private Category  category;
	
	private Date startDate, endDate;
	
	
	//CTORS
	

	public CouponDto(Coupon coupon) {
		this.id = coupon.getId();
		this.amount = coupon.getAmount();
		this.price = coupon.getPrice();
		this.description = coupon.getDescription();
		this.title = coupon.getTitle();
		this.image = coupon.getImage();
		this.startDate = coupon.getStartDate();
		this.endDate = coupon.getEndDate();
	}
	
	
}
