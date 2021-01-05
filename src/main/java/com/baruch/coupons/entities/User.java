package com.baruch.coupons.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="sur_name", nullable=false)
	private String surName;
	
	@Column(name="user_type", nullable=false)
	private UserType type;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Company company;
	
	@OneToMany(mappedBy = "user")
	private List<Purchase> purchases;
	
	@ManyToMany
	@JoinTable(name = "favorates", joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "vacation_id"))
	private Set<Coupon> favorates;
	
	public User() {
		
	}
	
	public User(UserDto userDto) {
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.firstName = userDto.getFirstName();
		this.surName = userDto.getSurName();
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
	

	public Set<Coupon> getFavorates() {
		return favorates;
	}

	public void setFavorates(Set<Coupon> favorates) {
		this.favorates = favorates;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", surName=" + surName + ", type=" + type + ", company=" + company + "]";
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
}
