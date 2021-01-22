package com.baruch.coupons.dataObjectsForPresentation;

import java.util.Date;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseDataForAdmin implements IPurchaseDataObject{
	
		//VARIABLES
	
		private int   amount;
		
		private Date  timeStamp;

		private float totalPrice;

		private String couponTitle, companyName;
		
		private String userName;
		
		//CTORS
		
		public PurchaseDataForAdmin(int amount, float price, Date timeStamp, String couponTitle, String companyName, String userName) {
			this.userName = userName;
			this.amount = amount;
			this.timeStamp = timeStamp;
			this.couponTitle = couponTitle;
			this.totalPrice = this.amount * price;
			this.companyName = companyName;
		}
		
		
}
