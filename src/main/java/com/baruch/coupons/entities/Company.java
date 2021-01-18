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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="companies")
@Getter
@Setter
@NoArgsConstructor
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
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<User> users;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Coupon> coupons;
	
	public Company(CompanyDto companyDto){
		this.name = companyDto.getName();
		this.address = companyDto.getAddress();
		this.phoneNumber = companyDto.getPhoneNumber();
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

	
	
}
