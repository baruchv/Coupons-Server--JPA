package com.baruch.coupons.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDetails {
	
	private String  userName, password;
	
	//METHODS
	
	public LoginDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

		
}
