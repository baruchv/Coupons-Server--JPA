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
import com.baruch.coupons.enums.Categories;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok's @NoArgsConstructor annotation is not used here due to it's eager fetch type. 

@Entity
@Table(name="coupons")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
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
	private Categories category;
	
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Column(name="end_date", nullable=false)
	private Date endDate;
	
	@ManyToOne()
	private Company company;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;
	
	public Coupon(CouponDto couponDto, Company company ) {
		this.amount =couponDto.getAmount();
		this.price = couponDto.getPrice();
		this.category = couponDto.getCategory();
		this.title = couponDto.getTitle();
		this.description = couponDto.getDescription();
		this.image = couponDto.getImage();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.company = company;
	}

}
