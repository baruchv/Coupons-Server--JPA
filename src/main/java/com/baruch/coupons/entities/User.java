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

import com.baruch.coupons.dto.user.UserDto;
import com.baruch.coupons.enums.UserTypes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok's @NoArgsConstructor annotation is not used here due to it's eager fetch type. 

@Entity
@Table(name="users")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
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
	private UserTypes type;
	
	@ToString.Exclude
	@ManyToOne()
	private Company company;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;
	
	public User(UserDto userDto, Company company) {
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.firstName = userDto.getFirstName();
		this.surName = userDto.getSurName();
		this.type = userDto.getType();
		this.company = company;
	}
	
}
