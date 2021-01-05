package com.baruch.coupons.dto;


import java.sql.Date;

import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Category;

public class CouponDto {
	
	//VARIABLES
	
	private int amount;
	
	private long  id, companyID;
	
	private float  price;
	
	private String  title, description, image;
	
	private Category  category;
	
	private Date startDate, endDate;
	
	
	//CTORS
	
	
	public CouponDto() {
		super();
	}
	
	public CouponDto(Coupon coupon) {
		this.id = coupon.getId();
		this.amount = coupon.getAmount();
		this.price = coupon.getPrice();
		this.description = coupon.getDescription();
		this.title = coupon.getTitle();
		this.image = coupon.getImage();
		this.startDate = coupon.getStartDate();
		this.endDate = coupon.getEndDate();
	}
	
	//METHODS
	
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

	public long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(long companyID) {
		this.companyID = companyID;
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
		return "CouponDto [amount=" + amount + ", id=" + id + ", companyID=" + companyID + ", price=" + price
				+ ", title=" + title + ", description=" + description + ", image=" + image + ", category=" + category
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
}
