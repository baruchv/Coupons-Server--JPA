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

import com.baruch.coupons.dto.dataInterfaces.IUserDataObject;
import com.baruch.coupons.dto.LoginDetails;
import com.baruch.coupons.dto.SuccessfulLoginData;
import com.baruch.coupons.dto.user.UserDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.UserTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.UsersController;

@RestController
@RequestMapping("/users")
public class UsersApi {
	
	@Autowired
	private UsersController controller;
	
	@PostMapping
	public long createUser(@RequestBody UserDto userDto) throws ApplicationException{
		return controller.createUser(userDto);
	}
	
	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody LoginDetails loginDetails) throws ApplicationException{
		return controller.login(loginDetails.getUserName(), loginDetails.getPassword());
	}
	
	@PostMapping("/logout")
	public void logout(@RequestHeader("Authorization") String token) {
		controller.logout(token);
		
	}
	
	@PutMapping("/firstName")
	public void updateFirstName(@RequestBody UserDto userDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		controller.updateFirstName(userDto.getFirstName(), userDetails);
	}

	@PutMapping("/surName")
	public void updateSurName(@RequestBody UserDto userDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException {
		controller.updateSurName(userDto.getSurName(), userDetails);
	}

	@PutMapping("/password")
	public void changePassword(@RequestBody UserDto userDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException {
		controller.changePasswoed(userDto.getPassword(), userDetails);
	}
	
	@GetMapping
	public List<IUserDataObject> getAllUsers() throws ApplicationException{
		return controller.getAllUsers();
	}

	@GetMapping("/account")
	public IUserDataObject getAccountDetails(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.getAccountDetails(userDetails);
	}
	
	@GetMapping("/{userID}")
	public IUserDataObject getUser(@PathVariable("userID") long userID) throws ApplicationException{
		return controller.getUser(userID);
	}
	
	@GetMapping("/byType")
	public List<IUserDataObject> getUsersByType(@RequestParam("type") UserTypes type) throws ApplicationException{
		return controller.getUsersByType(type);
	}
	
	@GetMapping("/byCompany")
	public List<IUserDataObject> getUsersByCompany(@RequestParam("companyID") long companyID) throws ApplicationException{
		return controller.getUsersByCompany(companyID);
	}
	
	@DeleteMapping
	public void deleteUser(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		controller.deleteUser(userDetails);
	}
	
}
