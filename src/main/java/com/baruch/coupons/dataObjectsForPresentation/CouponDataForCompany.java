package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Category;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDataForCompany implements ICouponDataObject{
	//VARIABLES

	private int amount;

	private long  id;

	private float  price;

	private String  title, description, image, companyName;

	private Category  category;

	private Date startDate, endDate;

	//CTROS

	/*
	 * Due to lazy fetch type the method getCompany is a DB query, therefore it
	 * might invoke an exception.
	 */
	public CouponDataForCompany(Coupon coupon) throws ApplicationException{
		try {
			this.id = coupon.getId();
			this.amount = coupon.getAmount();
			this.price = coupon.getPrice();
			this.description = coupon.getDescription();
			this.title = coupon.getTitle();
			this.image = coupon.getImage();
			this.startDate = coupon.getStartDate();
			this.category = coupon.getCategory();
			this.endDate = coupon.getEndDate();

			this.companyName = coupon.getCompany().getName();
		} catch (Exception e) {
			throw new ApplicationException("new CouponDataForCompany(Coupon) failed for " + coupon, ErrorTypes.GENERAL_ERROR,e);
		}
	}

}
