package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.dataInterfaces.IUserDataObject;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.UserType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

}
