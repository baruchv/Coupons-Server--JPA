package com.baruch.coupons.dto;

import com.baruch.coupons.entities.Company;

public class CompanyDto {
	
	//VARIABLES
	
	private String  name, address, phoneNumber;
	
	private long  id;
	
	
	//CTORS
	
	public CompanyDto() {
		super();
	}
	
	public CompanyDto(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.address = company.getAddress();
		this.phoneNumber = company.getPhoneNumber();
	}
	
	
	//METHODS
	
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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
