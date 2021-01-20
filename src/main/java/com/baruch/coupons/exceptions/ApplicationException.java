package com.baruch.coupons.exceptions;

import com.baruch.coupons.enums.ErrorTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends Exception {
	private Exception origin;
	ErrorTypes type;

	
	public ApplicationException(ErrorTypes type) {
		super(type.getMessage());
		this.type = type;
	}
	
	public ApplicationException(String message, ErrorTypes type) {
		super(message);
		this.type = type;
	}
	
	public ApplicationException(String message, ErrorTypes type, Exception origin) {
		super(message,origin);
		this.type = type;
	}

	
	
}
