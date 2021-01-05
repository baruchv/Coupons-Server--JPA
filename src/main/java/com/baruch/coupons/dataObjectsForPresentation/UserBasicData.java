package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.dataInterfaces.IUserDataObject;
import com.baruch.coupons.enums.UserType;

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
	
	//METHODS

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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserBasicData [id=" + id + ", userName=" + userName + ", type=" + type + "]";
	}
	
	
}
