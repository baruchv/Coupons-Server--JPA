package com.baruch.coupons.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baruch.coupons.dto.CompanyDto;

@Entity
@Table(name="companies")
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
	
	@OneToMany(mappedBy = "company")
	private List<User> users;
	
	@OneToMany(mappedBy = "company")
	private List<Coupon> coupons;
	
	public Company() {
		super();
	}

	public Company(CompanyDto companyDto){
		this.name = companyDto.getName();
		this.address = companyDto.getAddress();
		this.phoneNumber = companyDto.getPhoneNumber();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", users=" + users + ", coupons=" + coupons + "]";
	}

	
	
}
