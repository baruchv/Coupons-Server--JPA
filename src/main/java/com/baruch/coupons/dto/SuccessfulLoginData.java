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
public class SuccessfulLoginData {
	
	//VARIABLES

	private String token;
	
	private UserType type;
	
	//CTORS

	public SuccessfulLoginData(String token, UserType type) {
		super();
		this.token = token;
		this.type = type;
	}


}