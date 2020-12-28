package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dto.UserDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.entities.User;
import com.baruch.coupons.enums.UserType;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	
	public boolean existsByUserName(String userName);
	
	@Query("select new com.baruch.coupons.dto.UserDto(u) from User u where u.type = ?1")
	public List<UserDto> getUsersByType(UserType type);
	
	@Query("select new com.baruch.coupons.dto.UserDto(u) from User u where u.company.id = ?1")
	public List<UserDto> getUsersByCompany(long companyID);

	@Query("select new com.baruch.coupons.dto.UserDto(u) from User u where u.id = ?1")
	public UserDto getUser(long id);
	
	@Query("select new com.baruch.coupons.dto.UserLoginData(u) from User u where u.userName = ?1 and u.password = ?2")
	public UserLoginData login(String userName, String password);

	@Query("select new com.baruch.coupons.dto.UserDto(u) from User u")
	public List<UserDto> getAllUsers();
	
	@Modifying
	@Query("update User u set  u.password = ?1 where u.id = ?2 ")
	public void updateUser(String password, long id);
}
