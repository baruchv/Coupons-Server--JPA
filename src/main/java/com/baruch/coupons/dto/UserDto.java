package com.baruch.coupons.dto;

import com.baruch.coupons.enums.UserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	
	//VARIABLES
	
	private long id;
	
	private Long  companyID;
	
	private String  userName, password, firstName, surName;
	
	private UserType  type;

	
}
