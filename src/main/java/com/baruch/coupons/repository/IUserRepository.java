package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dataInterfaces.IUserDataObject;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.UserType;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	
	public boolean existsByUserName(String userName);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.UserBasicData(u.id, u.userName, u.type) from User u where u.type = ?1")
	public List<IUserDataObject> getUsersByType(UserType type);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.UserBasicData(u.id, u.userName, u.type) from User u where u.company.id = ?1")
	public List<IUserDataObject> getUsersByCompany(long companyID);
	
	@Query("select new com.baruch.coupons.dto.UserLoginData(u) from User u where u.userName = ?1 and u.password = ?2")
	public UserLoginData login(String userName, String password);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.UserBasicData(u.id, u.userName, u.type) from User u")
	public List<IUserDataObject> getAllUsers();
	
	@Modifying
	@Query("update User u set  u.password = ?1, u.firstName = ?2, u.surName = ?3 where u.id = ?4 ")
	public void updateUser(String password, String firstName, String surName, long userID);
	
	@Query("select u from User u where ?1 member of u.favorates")
	public List<User> getUsersByFavorateCoupon(Coupon coupon);
}
