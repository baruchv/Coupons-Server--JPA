package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public void updateUser(@RequestBody UserDto userDto, UserLoginData userDetails) throws ApplicationException{
		con.updateUser(userDto, userDetails);
	}
	
	@GetMapping
	public List<UserDto> getAllUsers() throws ApplicationException{
		return con.getAllUsers();
	}
	
	@GetMapping("/{userID}")
	public UserDto getUser(@PathVariable("userID") long id) throws ApplicationException{
		return con.getUser(id);
	}
	
	@GetMapping("/byType")
	public List<UserDto> getUsersByType(@RequestParam("type") UserType type) throws ApplicationException{
		return con.getUsersByType(type);
	}
	
	@GetMapping("/byCompany")
	public List<UserDto> getUsersByCompany(@RequestParam("companyID") long companyID) throws ApplicationException{
		return con.getUsersByCompany(companyID);
	}
	
	@DeleteMapping
	public void deleteUser(UserLoginData userDetails) throws ApplicationException{
		con.deleteUser(userDetails);
	}
	
}
