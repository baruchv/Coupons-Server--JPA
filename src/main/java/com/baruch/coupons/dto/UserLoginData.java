package com.baruch.coupons.dto;

import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserType;
import com.baruch.coupons.exceptions.ApplicationException;

public class UserLoginData {
	
	private long id;
	private Long companyID;
	private UserType type;
	
	/*
	 * Due to laze fetch type the method getCompany is a DB query, therefore it
	 * might invoke an exception.
	 */
	public UserLoginData(User user) throws ApplicationException{
		try {
			this.id = user.getId();
			this.type = user.getType();
			if(type.equals(UserType.COMPANY)) {
				this.companyID = user.getCompany().getId();
			}
		} catch (Exception e) {
			throw new ApplicationException("new UserLoginData(User) failed for " + user, ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserLoginData [id=" + id + ", companyID=" + companyID + ", type=" + type + "]";
	}
	
	
}
