package com.baruch.coupons.dto;

import com.baruch.coupons.enums.UserTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SuccessfulLoginData {
	
	//VARIABLES

	private String token;
	
	private UserTypes type;
	
	//CTORS

	public SuccessfulLoginData(String token, UserTypes type) {
		super();
		this.token = token;
		this.type = type;
	}


}