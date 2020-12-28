package com.baruch.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baruch.coupons.dto.UserDto;
import com.baruch.coupons.enums.UserType;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="username", nullable=false,unique=true)
	private String userName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="user_type", nullable=false)
	private UserType type;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Company company;
	
	@OneToMany(mappedBy = "user")
	List<Purchase> purchases;
	
	public User() {
		
	}
	
	public User(UserDto userDto) {
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.type = userDto.getType();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", type=" + type + ", company="
				+ company + "]";
	}
	
}