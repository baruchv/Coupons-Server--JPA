package com.baruch.coupons.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baruch.coupons.dto.PurchaseDto;

@Entity
@Table(name="purchases")
public class Purchase {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="amount", nullable=false)
	private int amount;
	
	@Column(name="time_stamp", nullable=false)
	private Date timeStamp;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private User user;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Coupon coupon;
	
	public Purchase() {
		
	}
	
	public Purchase(PurchaseDto purchaseDto, Coupon coupon, User user) {
		this.coupon = coupon;
		this.user = user;
		this.amount = purchaseDto.getAmount();
		this.timeStamp = purchaseDto.getTimeStamp();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", amount=" + amount + ", timeStamp=" + timeStamp + ", user=" + user + ", coupon="
				+ coupon + "]";
	}
	
	
	
}
