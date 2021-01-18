package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.Purchase;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

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
		
		/*
		 * Due to Lazy FetchType, the method getCoupon() is a DB query.
		 * Therefore, it might invoke an exception.
		 */
		public PurchaseDataForCompanyUser(Purchase purchase) throws ApplicationException{
			try {
				this.amount = purchase.getAmount();
				this.timeStamp = purchase.getTimeStamp();
				Coupon coupon = purchase.getCoupon();
				this.couponTitle = coupon.getTitle();
				this.totalPrice = this.amount * coupon.getPrice();
			} catch (Exception e) {
				throw new ApplicationException("new PurchaseDataforCompanyUser(Purchase) failed for " + purchase, ErrorTypes.GENERAL_ERROR,e);
			}
		}
		
	
}
