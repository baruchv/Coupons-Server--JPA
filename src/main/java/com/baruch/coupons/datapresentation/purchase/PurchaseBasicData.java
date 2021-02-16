package com.baruch.coupons.datapresentation.purchase;

import java.util.Date;

import com.baruch.coupons.datapresentation.dataInterfaces.IPurchaseDataObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseBasicData implements IPurchaseDataObject {
	
		//VARIABLES
		private long id;

		private int   amount;
		
		private Date  timeStamp;

		private float totalPrice;

		private String couponTitle;
		
		//CTORS
		
		public PurchaseBasicData(long id, int amount, Date timeStamp, float price, String couponTitle) {
			this.id = id;
			this.amount = amount;
			this.timeStamp = timeStamp;
			this.totalPrice = amount * price;
			this.couponTitle = couponTitle;
		}
		
	
}
