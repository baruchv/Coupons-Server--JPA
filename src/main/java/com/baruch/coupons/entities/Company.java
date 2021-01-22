package com.baruch.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baruch.coupons.dto.CompanyDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok's @NoArgsConstructor annotation is not used here due to it's eager fetch type. 

@Entity
@Table(name="companies")
@Getter
@Setter
@ToString
public class Company {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@Column(name="address", nullable=false)
	private String address;
	
	@Column(name="phone_number", nullable=false)
	private String phoneNumber;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<User> users;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Coupon> coupons;

	public Company(){

	}
	
	public Company(CompanyDto companyDto){
		this.name = companyDto.getName();
		this.address = companyDto.getAddress();
		this.phoneNumber = companyDto.getPhoneNumber();
	}

}
