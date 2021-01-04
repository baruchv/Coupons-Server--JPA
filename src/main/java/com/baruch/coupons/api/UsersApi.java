package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.dataObjectsForPresentation.UserData;
import com.baruch.coupons.dto.LoginDetails;
import com.baruch.coupons.dto.SuccessfulLoginData;
import com.baruch.coupons.dto.UserDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.UserType;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.UsersController;

@RestController
@RequestMapping("/users")
public class UsersApi {
	
	@Autowired
	private UsersController con;
	
	@PostMapping
	public long createUser(@RequestBody UserDto userDto) throws ApplicationException{
		return con.createUser(userDto);
	}
	
	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody LoginDetails loginDetails) throws ApplicationException{
		return con.login(loginDetails.getUserName(), loginDetails.getPassword());
	}
	
	@PostMapping("/logout")
	public void logout(@RequestHeader("Authorization") String token) {
		con.logout(token);
		
	}
	
	@PutMapping
	public void updateUser(@RequestBody UserDto userDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		con.updateUser(userDto, userDetails);
	}
	
	@GetMapping
	public List<UserData> getAllUsers() throws ApplicationException{
		return con.getAllUsers();
	}
	
	@GetMapping("/{userID}")
	public UserData getUser(@PathVariable("userID") long userID) throws ApplicationException{
		return con.getUser(userID);
	}
	
	@GetMapping("/byType")
	public List<UserData> getUsersByType(@RequestParam("type") UserType type) throws ApplicationException{
		return con.getUsersByType(type);
	}
	
	@GetMapping("/byCompany")
	public List<UserData> getUsersByCompany(@RequestParam("companyID") long companyID) throws ApplicationException{
		return con.getUsersByCompany(companyID);
	}
	
	@DeleteMapping
	public void deleteUser(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		con.deleteUser(userDetails);
	}
	
}
