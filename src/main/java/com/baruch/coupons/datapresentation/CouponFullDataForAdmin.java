package com.baruch.coupons.datapresentation;

import java.util.Date;


import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.enums.Categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CouponFullDataForAdmin implements ICouponDataObject {

	//VARIABLES

	private int amount;

	private long  id, companyID;

	private float  price;

	private String  title, description, image, companyName;

	private Categories  category;

	private Date startDate, endDate;
	
	
}
