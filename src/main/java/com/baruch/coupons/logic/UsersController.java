package com.baruch.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.dto.dataInterfaces.IUserDataObject;
import com.baruch.coupons.dto.SuccessfulLoginData;
import com.baruch.coupons.dto.user.UserDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.enums.UserTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.repository.IUserRepository;

@Controller
public class UsersController {
	
	//PROPERTIES

	@Autowired
	private IUserRepository repository;

	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private CacheController cache;

	private final String HASHINGEXTENTION = "DASF;lkpoi493i@@#$%*21"; 
	
	//PUBLIC-METHODS

	public long createUser(UserDto userDto) throws ApplicationException{
		validateCreateUser(userDto);
		String hashedPassword = getHashedPassword(userDto.getPassword());
		userDto.setPassword(hashedPassword);
		User user = generateEntity(userDto);
		try {
			repository.save(user);
			return user.getId();
		}
		catch(Exception e) {
			throw new ApplicationException("createUser failed for " + userDto, ErrorTypes.GENERAL_ERROR, e);
		}
	}

	@Transactional 
	public void changePasswoed(String password, UserLoginData userDetails) throws ApplicationException{
		validatePassword(password);
		long userID = userDetails.getId();
		String hashedPassword = getHashedPassword(password);
		try {
			repository.changePassword(hashedPassword, userID);
		} catch (Exception e) {
			throw new ApplicationException("changePassword() failed for userID: " + userID + " password: " + password, ErrorTypes.GENERAL_ERROR, e);
		}
	}

	@Transactional
	public void updateFirstName(String firstName, UserLoginData userDetails) throws ApplicationException {
		validateName(firstName);
		long userID = userDetails.getId();
		try {
			repository.updateFirstName(firstName, userID);
		} catch (Exception e) {
			throw new ApplicationException("updateFirstName() failed for userID: " + userID + " firstName: " + firstName,
					ErrorTypes.GENERAL_ERROR, e);
		}
	}

	@Transactional
	public void updateSurName(String surName, UserLoginData userDetails) throws ApplicationException {
		validateName(surName);
		long userID = userDetails.getId();
		try {
			repository.updateSurName(surName, userID);
		} catch (Exception e) {
			throw new ApplicationException("updateSurName() failed for userID: " + userID + " firstName: " + surName,
					ErrorTypes.GENERAL_ERROR, e);
		}
	}

	public void deleteUser(UserLoginData userDetails) throws ApplicationException{
		long userID = userDetails.getId();
		try {
			repository.deleteById(userID);
		}
		catch(Exception e) {
			throw new ApplicationException("deleteById() failed for userID = " + userID, ErrorTypes.GENERAL_ERROR,e);
		}
	}

	//This method serves all users,
	public IUserDataObject getAccountDetails(UserLoginData userDetails) throws ApplicationException{
		long userID = userDetails.getId();
		return getUser(userID);
	}

	//This method serves Admin users only.
	public IUserDataObject getUser(long userID) throws ApplicationException{
		validateUserId(userID);
		try {
			
			UserTypes type = repository.getUserType(userID);
			
			if(type.equals(UserTypes.COMPANY)) {
				return repository.getCompanyUser(userID);
			}
			else {
				return repository.getDefaultUser(userID);
			}
		}
		catch(Exception e) {
			throw new ApplicationException("findById() failed for userID = " + userID, ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	public List<IUserDataObject> getAllUsers() throws ApplicationException{
		try {
			return repository.getAllUsers();
		}
		catch(Exception e) {
			throw new ApplicationException("findAll() failed for users table", ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public List<IUserDataObject> getUsersByCompany(long companyID) throws ApplicationException{
		try {
			return repository.getUsersByCompany(companyID);
		}
		catch(Exception e) {
			throw new ApplicationException("findAll() failed for users table", ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public List<IUserDataObject> getUsersByType(UserTypes type) throws ApplicationException{
		try {
			return repository.getUsersByType(type);
		}
		catch(Exception e) {
			throw new ApplicationException("findAll() failed for users table", ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public SuccessfulLoginData login(String userName, String password) throws ApplicationException {
		UserLoginData userDetails = repository.login(userName, getHashedPassword(password));
		if(userDetails == null) {
			throw new ApplicationException(ErrorTypes.LOGIN_ERROR);
		}
		String token = generateToken(userName, password);
		cache.put(token, userDetails);
		return new SuccessfulLoginData(token, userDetails.getType());
	}

	public void logout(String token) {
		cache.remove(token);
	}

	//PRIVATE-METHODS

	void save(User user) throws ApplicationException{
		try {
			repository.save(user);
		} catch (Exception e) {
			throw new ApplicationException("UsersController.save failed for " + user,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	User getUserEntity(long userID) throws ApplicationException{
		try {
			return repository.findById(userID).get();
		}
		catch(Exception e) {
			throw new ApplicationException("repository.findById() failed for userID = " + userID, ErrorTypes.GENERAL_ERROR, e);
		}
	}
	
	void validateUserId(long userID) throws ApplicationException{
		try {
			if( ! repository.existsById(userID)) {
				throw new ApplicationException("UsersController.validateUserID() failed for ID: " + userID, ErrorTypes.NO_USER_FOUND);
			}
		}
		catch(Exception e) {
			throw new ApplicationException("existsById failed for userID = " + userID, ErrorTypes.GENERAL_ERROR,e);
		}
	}

	private void validateCreateUser(UserDto userDto) throws ApplicationException{
		if(userDto.getType() == null){
			throw new ApplicationException(ErrorTypes.NO_TYPE_ERROR);
		}
		validateUserName(userDto.getUserName());
		validatePassword(userDto.getPassword());
		validateNames(userDto.getFirstName(), userDto.getSurName());
	}

	private String getHashedPassword(String password ) {
		return String.valueOf((password + HASHINGEXTENTION ).hashCode());
	}

	private void validateUserName(String userName) throws ApplicationException{
		
		try {
			if(repository.existsByUserName(userName)){
				throw new ApplicationException(ErrorTypes.EXISTING_USERNAME_ERROR);
			}
		} catch (Exception e) {
			if(e instanceof ApplicationException){
				throw e;
			}
			throw new ApplicationException("validateUserName() failed for username: " + userName, ErrorTypes.GENERAL_ERROR,e);
		}
		
		if(userName == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_USERNAME_ERROR);
		}
		
		if(userName.length()<2) {
			throw new ApplicationException(ErrorTypes.INVALID_USERNAME_ERROR);
		}
	}
	
	private void validateNames(String firstName, String surName) throws ApplicationException{
		validateName(firstName);
		validateName(surName);
	}

	private void validateName(String name) throws ApplicationException{
		if(name == null){
			throw new ApplicationException(ErrorTypes.EMPTY_NAME_ERROR);
		}
		if(name.length() < 2){
			throw new ApplicationException(ErrorTypes.INVALID_NAME_ERROR);
		}
	}

	private void validatePassword(String password) throws ApplicationException{
		if(password == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_PASSWORD_ERROR);
		}
		if(password.length()<8) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD_ERROR);
		}
	}

	private String generateToken(String userName, String password) {
		Calendar now = Calendar.getInstance();
		int token = (userName + password + now.getTime().toString() + HASHINGEXTENTION).hashCode();
		return Integer.toString(token);
	}

	private User generateEntity(UserDto userDto) throws ApplicationException{
		try {
			Long companyID = userDto.getCompanyID();
			Company company = null;
			if (companyID != null) {
				company = companiesController.getCompanyEntity(companyID);
			}
			User user = new User(userDto,company);
			return user;
		} catch (Exception e) {
			throw new ApplicationException("usersController.generateEntity() failed for " + userDto,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	

}
