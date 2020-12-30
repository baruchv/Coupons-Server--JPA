package com.baruch.coupons.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.enums.Category;

@Entity
@Table(name="coupons")
public class Coupon {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="image", nullable=true)
	private String image;
	
	@Column(name="amount",nullable = false)
	private int amount;
	
	@Column(name="price", nullable=false)
	private float price;
	
	@Column(name="category", nullable=false)
	private Category category;
	
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Column(name="end_date", nullable=false)
	private Date endDate;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Company company;
	
	@OneToMany(mappedBy = "coupon")
	private List<Purchase> purchases;
	
	public Coupon() {
		
	}
	
	public Coupon(CouponDto couponDto) {
		this.amount =couponDto.getAmount();
		this.price = couponDto.getPrice();
		this.category = couponDto.getCategory();
		this.title = couponDto.getTitle();
		this.description = couponDto.getDescription();
		this.image = couponDto.getImage();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", description=" + description + ", image=" + image
				+ ", amount=" + amount + ", price=" + price + ", category=" + category + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", company=" + company + ", purchases=" + purchases + "]";
	}

	
	
	
}
