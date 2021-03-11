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
public class UserFullDataCompany implements IUserDataObject{
		
		//VARIABLES
		
		private long id, companyID;

		private String  userName, copmanyName, firstName, surName;
		
		private UserTypes  type;
		
}
