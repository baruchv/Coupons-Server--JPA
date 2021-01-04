package com.baruch.coupons.dataObjectsForPresentation;

import java.sql.Date;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Category;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "CouponDataForCompany [amount=" + amount + ", id=" + id + ", price=" + price + ", title=" + title
				+ ", description=" + description + ", image=" + image + ", companyName=" + companyName + ", category="
				+ category + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
