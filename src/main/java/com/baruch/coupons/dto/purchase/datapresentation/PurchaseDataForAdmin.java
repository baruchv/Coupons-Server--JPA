package com.baruch.coupons.dto.purchase.datapresentation;

import java.util.Date;

import com.baruch.coupons.dto.dataInterfaces.IPurchaseDataObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseDataForAdmin implements IPurchaseDataObject{
	
		//VARIABLES

		private long id;
	
		private int   amount;
		
		private Date  timeStamp;

		private float totalPrice;

		private String couponTitle, companyName;
		
		private String userName;
		
		//CTORS
		
		public PurchaseDataForAdmin(long id, int amount, float price, Date timeStamp, String couponTitle, String companyName, String userName) {
			this.id = id;
			this.userName = userName;
			this.amount = amount;
			this.timeStamp = timeStamp;
			this.couponTitle = couponTitle;
			this.totalPrice = this.amount * price;
			this.companyName = companyName;
		}
		
		
}
