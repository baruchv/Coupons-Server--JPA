package com.baruch.coupons.enums;

public enum ErrorTypes {
	
	//TYPES
	
	NO_COMPANY_ID(301,"sorry",true),
	
	NO_USER_ID(302,"sorry",true),
	
	NO_COUPON_ID(303,"",true),
	
	NO_PURCHASE_ID(304,"",true),
	
	EMPTY_PHONENUMBER_ERROR(305,"Must enter a phone number",true),
	
	INVALID_PHONENUMBER_ERROR(306,"Phone number must be at least 9 digits long",true),
	
	EMPTY_ADDRESS_ERROR(307,"Must enter an address",true),
	
	INAVLID_ADDRESS_ERROR(308,"An address string must be at least 2 characters long",true),
	
	EMPTY_NAME_ERROR(309,"Must enter a name",true),
	
	INVALID_NAME_ERROR(310,"A name string must be at least 2 characters long",true),
	
	EXISTING_COMPANY_ERROR(311,"This Company already exists",true),
	
	EXISTING_USERNAME_ERROR(312,"This User-Name already exists",true),
	
	EMPTY_USERNAME_ERROR(313,"Must enter a User-Name",true),
	
	INVALID_USERNAME_ERROR(314,"User-Name string must be at least 2 characters long",true),
	
	EMPTY_PASSWORD_ERROR(315,"Must enter a password",true),
	
	INVALID_PASSWORD_ERROR(316,"Password string should be at least 8 characters long",true),
	
	EMPTY_COMPANYID_ERROR(317,"Company users must have a company ID",true),
	
	EMPTY_TITLE_ERROR(318,"Must enter coupon's title",true),
	
	INVALID_TITLE_ERROR(319,"Coupon's title string must be at least 2 characters long",true),
	
	EMPTY_DESCRIPTION_ERROR(320,"Must enter coupon's description",true),
	
	INVALID_DESCRIPTION_ERROR(321,"Coupon's description must be at least 2 characters long",true),
	
	INVALID_STARTDATE_ERROR(322,"Start date had already past",true),
	
	INVALID_ENDDATE_ERROR(323,"End-date had already past",true),
	
	INVALID_DATES_ERROR(324,"End-date must be after start_date",true),
	
	EMPTY_ENDDATE_ERROR(325,"Must eneter a end-date",true),
	
	INVALID_AMOUNT_ERROR(326,"The amount value must be at least 1",true),
	
	INVALID_PRICE_ERROR(327,"The price cannot be negative",true),
	
	INVALID_COUPON_ERROR(328,"Sorry, the desired coupon was expired",true),
	
	OUT_OF_STOCK_ERROR(329,"there are not enough coupons in stock",true),
	
	DUPLICATE_TITLE_ERROR(330,"Your company has already a coupon with the same title",true),
	
	LOGIN_ERROR(331,"Sorry, Wrong user-name or password",true),

	GENERAL_ERROR(501, "Sorry, our services are currently unavailable", true),
	
	TIMER_TASK_ERROR(502,"sorry",false),
	
	AMOUNT_RESTORING_ERROR(503,"System could'nt restore coupon's amount after deleting purchase",false);
	
	//VERIABLES
	
	private boolean  isPresented;
	
	private String  message;
	
	private int  errorNumber;
	
	//CTORS
	
	private ErrorTypes(int serialNumber,String message,boolean isPresented)  {
		this.isPresented = isPresented;
		this.message = message;
		this.errorNumber = serialNumber;
	}

	// METHODS

	public boolean isPresented() {
		return isPresented;
	}

	public String getMessage() {
		return message;
	}

	public int getSerialNumber() {
		return errorNumber;
	}
	
	
}
	