package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dto.dataInterfaces.IUserDataObject;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.UserTypes;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	
	public boolean existsByUserName(String userName);
	
	@Query("select new com.baruch.coupons.datapresentation.user.UserBasicData(u.id, u.userName, u.type) from User u where u.type = ?1")
	public List<IUserDataObject> getUsersByType(UserTypes type);
	
	@Query("select new com.baruch.coupons.datapresentation.user.UserBasicData(u.id, u.userName, u.type) from User u where u.company.id = ?1")
	public List<IUserDataObject> getUsersByCompany(long companyID);
	
	@Query("select new com.baruch.coupons.dto.UserLoginData(u) from User u where u.userName = ?1 and u.password = ?2")
	public UserLoginData login(String userName, String password);

	@Query("select new com.baruch.coupons.datapresentation.user.UserBasicData(u.id, u.userName, u.type) from User u")
	public List<IUserDataObject> getAllUsers();

	@Query("select new com.baruch.coupons.datapresentation.user.UserFullDataCompany"+
	"(u.id, u.company.id, u.userName, u.company.name, u.firstName, u.surName, u.type) from User u where u.id = ?1")
	public IUserDataObject getCompanyUser(long userID);

	@Query("select new com.baruch.coupons.datapresentation.user.UserFullDataDefault"+ 
	"(u.id, u.userName, u.firstName, u.surName, u.type) from User u where u.id = ?1")
	public IUserDataObject getDefaultUser(long userID);

	@Query("select u.type from User u where u.id = ?1")
	public UserTypes getUserType(long userID);

	@Modifying
	@Query("update User u set  u.password = ?1 where u.id = ?2 ")
	public void changePassword(String password,long userID);

	@Modifying
	@Query("update User u set u.firstName = ?1 where u.id = ?2 ")
	public void updateFirstName(String firstName,long userID);
	
	@Modifying
	@Query("update User u set u.surName = ?1 where u.id = ?2 ")
	public void updateSurName(String surName, long userID);
}
