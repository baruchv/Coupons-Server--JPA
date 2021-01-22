package com.baruch.coupons.dataObjectsForPresentation;

import java.util.Date;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;

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
	
	public PurchaseDataForCustomer(int amount, Date timeStamp, float price, String couponTitle, String companyName){
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.totalPrice = amount * price;
		this.couponTitle = couponTitle;
		this.companyName = companyName;
	}
	
}
