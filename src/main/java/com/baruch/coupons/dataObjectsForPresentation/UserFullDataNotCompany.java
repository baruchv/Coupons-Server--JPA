package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.dataInterfaces.IUserDataObject;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.UserType;

public class UserFullDataNotCompany implements IUserDataObject{

	//VARIABLES

	private long id;

	private String  userName, firstName, surName;

	private UserType  type;

	//CTORS
	
	public UserFullDataNotCompany(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.type = user.getType();
		this.firstName = user.getFirstName();
		this.surName = user.getSurName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	

}
