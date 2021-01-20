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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
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
	
	@ManyToOne()
	private Company company;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;
	
	@ManyToMany
	@JoinTable(name = "favorites", joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "vacation_id"))
	private Set<Coupon> favorites;
	
	public User(UserDto userDto) {
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.firstName = userDto.getFirstName();
		this.surName = userDto.getSurName();
		this.type = userDto.getType();
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
