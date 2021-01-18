package com.baruch.coupons.dto;

import com.baruch.coupons.entities.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyDto {
	
	//VARIABLES
	
	private String  name, address, phoneNumber;
	
	private long  id;
	
	
	//CTORS
	
	public CompanyDto(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.address = company.getAddress();
		this.phoneNumber = company.getPhoneNumber();
	}
	
}
