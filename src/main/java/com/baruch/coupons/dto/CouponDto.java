package com.baruch.coupons.dto;


import java.sql.Date;

import com.baruch.coupons.enums.Categories;

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
	
	private long  companyID;
	
	private float  price;
	
	private String  title, description, image;
	
	private Categories  category;
	
	private Date startDate, endDate;
	
}
