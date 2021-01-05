package com.baruch.coupons.dto;

import com.baruch.coupons.enums.UserType;

public class UserDto {
	
	//VARIABLES
	
	private long id;
	
	private Long  companyID;
	
	private String  userName, password, firstName, surName;
	
	private UserType  type;

	
	//CTORS
	
	
	public UserDto() {
		super();
	}
	
	//METHODS
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public UserType getType() {
		return type;
	}
	
	public void setType(UserType type) {
		this.type = type;
	}
	
	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", companyID=" + companyID + ", userName=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", surName=" + surName + ", type=" + type + "]";
	}

	
	
}
