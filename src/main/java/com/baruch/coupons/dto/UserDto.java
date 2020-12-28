package com.baruch.coupons.dto;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserType;
import com.baruch.coupons.exceptions.ApplicationException;

public class UserDto {
	
	//VARIABLES
	
	private long id;
	
	private Long  companyID;
	
	private String  userName, password, copmanyName;;
	
	private UserType  type;

	
	//CTORS
	
	
	public UserDto() {
		super();
	}

	/*
	 * Due to laze fetch type the method getCompany is a DB query, therefore it
	 * might invoke an exception.
	 */	
	public UserDto(User user) throws ApplicationException{
		try{
			this.id = user.getId();
			this.userName = user.getUserName();
			this.type = user.getType();
			Company company = user.getCompany();
			if (company != null){
			this.copmanyName = company.getName();
			}
		}
		catch(Exception e){
			throw new ApplicationException("new UserDto(User) failed for " + user, ErrorTypes.GENERAL_ERROR,e);
		}
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

	public String getCopmanyName() {
		return copmanyName;
	}

	public void setCopmanyName(String copmanyName) {
		this.copmanyName = copmanyName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", id=" + id + ", type=" + type
				+ ", companyID=" + companyID + ", companyName=" + copmanyName + "]";
	}
	
	
	
}
