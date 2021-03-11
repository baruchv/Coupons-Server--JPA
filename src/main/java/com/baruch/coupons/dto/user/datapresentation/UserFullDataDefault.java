package com.baruch.coupons.dto.user.datapresentation;

import com.baruch.coupons.dto.dataInterfaces.IUserDataObject;
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
