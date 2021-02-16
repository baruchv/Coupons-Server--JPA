package com.baruch.coupons.datapresentation.user;

import com.baruch.coupons.datapresentation.dataInterfaces.IUserDataObject;
import com.baruch.coupons.enums.UserTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserFullDataDefault implements IUserDataObject{

	//VARIABLES

	private long id;

	private String  userName, firstName, surName;

	private UserTypes  type;

}
