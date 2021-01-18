package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.dataInterfaces.IUserDataObject;
import com.baruch.coupons.enums.UserType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBasicData implements IUserDataObject{
	
	//VARIABLES
	
	private long id;
	
	private String  userName;
	
	private UserType  type;
	
	//CTORS
	
	public UserBasicData(long id, String userName, UserType type) {
		super();
		this.id = id;
		this.userName = userName;
		this.type = type;
	}
	
	
}
