package com.baruch.coupons.datapresentation;

import java.util.Date;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseDataForCompanyUser implements IPurchaseDataObject {
	
		//VARIABLES
	
		private int   amount;
		
		private Date  timeStamp;

		private float totalPrice;

		private String couponTitle;
		
		//CTORS
		
		public PurchaseDataForCompanyUser(int amount, Date timeStamp, float price, String couponTitle) {
			this.amount = amount;
			this.timeStamp = timeStamp;
			this.totalPrice = amount * price;
			this.couponTitle = couponTitle;
		}
		
	
}
