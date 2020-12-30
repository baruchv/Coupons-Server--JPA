package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserType;
import com.baruch.coupons.exceptions.ApplicationException;

public class UserData {
		
		//VARIABLES
	
		private long id;
		
		private Long  companyID;
		
		private String  userName, copmanyName;;
		
		private UserType  type;
		
		
		/*
		 * Due to lazy fetch type the method getCompany is a DB query, therefore it
		 * might invoke an exception.
		 */	
		public UserData(User user) throws ApplicationException{
			try {
				this.id = user.getId();
				this.userName = user.getUserName();
				this.type = user.getType();
				if( type.equals(UserType.COMPANY)) {
					Company company = user.getCompany();
					this.companyID = company.getId();
					this.copmanyName = company.getName();
				}
			} catch (Exception e) {
				throw new ApplicationException("new UserData(User) failed for " + user, ErrorTypes.GENERAL_ERROR,e);
			}
		}


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public Long getCompanyID() {
			return companyID;
		}


		public void setCompanyID(Long companyID) {
			this.companyID = companyID;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getCopmanyName() {
			return copmanyName;
		}


		public void setCopmanyName(String copmanyName) {
			this.copmanyName = copmanyName;
		}


		public UserType getType() {
			return type;
		}


		public void setType(UserType type) {
			this.type = type;
		}
		
		
}
