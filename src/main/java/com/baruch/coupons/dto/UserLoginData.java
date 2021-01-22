package com.baruch.coupons.dto;

import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserTypes;
import com.baruch.coupons.exceptions.ApplicationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginData {
	
	private long id;

	private UserTypes type;

	private Long companyID;

	
	/*
	 * Due to laze fetch type the method getCompany is a DB query, therefore it
	 * might invoke an exception.
	 */
	public UserLoginData(User user) throws ApplicationException{
		try {
			this.id = user.getId();
			this.type = user.getType();
			if(type.equals(UserTypes.COMPANY)) {
				this.companyID = user.getCompany().getId();
			}
		} catch (Exception e) {
			throw new ApplicationException("new UserLoginData(User) failed for " + user, ErrorTypes.GENERAL_ERROR,e);
		}
	}
}
