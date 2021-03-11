package com.baruch.coupons.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baruch.coupons.dto.purchase.PurchaseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok's @NoArgsConstructor annotation is not used here due to it's eager fetch type. 

@Entity
@Table(name="purchases")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
	
	public Purchase(PurchaseDto purchaseDto, Coupon coupon, User user) {
		this.coupon = coupon;
		this.user = user;
		this.amount = purchaseDto.getAmount();
		this.timeStamp = purchaseDto.getTimeStamp();
	}
		
}
