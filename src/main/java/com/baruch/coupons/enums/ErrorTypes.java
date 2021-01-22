package com.baruch.coupons.enums;

public enum ErrorTypes {
	
	//TYPES
	
	NO_PURCHASE_ID(301,"sorry there is no coupon with the specified id",false),
	
	EMPTY_PHONENUMBER_ERROR(302,"Must enter a phone number",false),
	
	INVALID_PHONENUMBER_ERROR(303,"Phone number must be at least 9 digits long",false),
	
	EMPTY_ADDRESS_ERROR(304,"Must enter an address",false),
	
	INAVLID_ADDRESS_ERROR(305,"An address string must be at least 2 characters long",false),
	
	EMPTY_NAME_ERROR(306,"Must enter a name",false),
	
	INVALID_NAME_ERROR(307,"A name string must be at least 2 characters long",false),
	
	EXISTING_COMPANY_ERROR(308,"This Company already exists",false),
	
	EXISTING_USERNAME_ERROR(309,"This User-Name already exists",false),
	
	EMPTY_USERNAME_ERROR(310,"Must enter a User-Name",false),
	
	INVALID_USERNAME_ERROR(311,"User-Name string must be at least 2 characters long",false),
	
	EMPTY_PASSWORD_ERROR(312,"Must enter a password",false),
	
	INVALID_PASSWORD_ERROR(313,"Password string should be at least 8 characters long",false),
	
	EMPTY_COMPANYID_ERROR(314,"Company users must have a company ID",false),
	
	EMPTY_TITLE_ERROR(315,"Must enter coupon's title",false),
	
	INVALID_TITLE_ERROR(316,"Coupon's title string must be at least 2 characters long",false),
	
	EMPTY_DESCRIPTION_ERROR(317,"Must enter coupon's description",false),
	
	INVALID_DESCRIPTION_ERROR(318,"Coupon's description must be at least 2 characters long",false),
	
	INVALID_STARTDATE_ERROR(319,"Start date had already past",false),
	
	INVALID_ENDDATE_ERROR(320,"End-date had already past",false),
	
	INVALID_DATES_ERROR(321,"End-date must be after start_date",false),
	
	EMPTY_ENDDATE_ERROR(322,"Must eneter a end-date",false),
	
	INVALID_AMOUNT_ERROR(323,"The amount value must be at least 1",false),
	
	INVALID_PRICE_ERROR(324,"The price cannot be negative",false),
	
	OUT_OF_STOCK_ERROR(325,"there are not enough coupons in stock",false),
	
	DUPLICATE_TITLE_ERROR(326,"Your company has already a coupon with the same title",false),
	
	LOGIN_ERROR(327,"Sorry, Wrong user-name or password",false),

	//Those errors might indicate that a user was trying to bypass the client,
	// therefore the technical teams should know.

	NO_COMPANY_ID(328, "sorry there is no company with the specified id", true),

	NO_USER_ID(329, "sorry there is no user with the specified id", true),

	NO_COUPON_ID(330, "sorry there is no coupon with the specified id", true),

	INVALID_COUPON_ERROR(331, "Sorry, the desired coupon is not valid", true),

	//This error indicate a technical problem, therefore the technical teams should know.

	GENERAL_ERROR(332, "Sorry, our services are currently unavailable", true);
	
	//VERIABLES
	
	private boolean  shouldPrintStackTrace;
	
	private String  message;
	
	private int  errorNumber;
	
	//CTORS
	
	private ErrorTypes(int serialNumber,String message,boolean shouldPrintStackTrace)  {
		this.shouldPrintStackTrace = shouldPrintStackTrace;
		this.message = message;
		this.errorNumber = serialNumber;
	}

	// METHODS

	public boolean shouldPrintStackTrace() {
		return shouldPrintStackTrace;
	}

	public String getMessage() {
		return message;
	}

	public int getSerialNumber() {
		return errorNumber;
	}
	
	
}
	