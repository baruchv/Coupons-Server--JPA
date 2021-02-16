package com.baruch.coupons.datapresentation.user;

import com.baruch.coupons.datapresentation.dataInterfaces.IUserDataObject;
import com.baruch.coupons.enums.UserTypes;

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
	
	private UserTypes  type;
	
	//CTORS
	
	public UserBasicData(long id, String userName, UserTypes type) {
		super();
		this.id = id;
		this.userName = userName;
		this.type = type;
	}
	
	
}
