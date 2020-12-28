package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dto.CompanyDto;
import com.baruch.coupons.entities.Company;

@Repository
public interface ICompaniesRepository extends CrudRepository<Company, Long>{

	@Query("select new com.baruch.coupons.dto.CompanyDto(c) from Company c where c.id = ?1")
	public CompanyDto getCompany(long id);

	@Query("select new com.baruch.coupons.dto.CompanyDto(c) from Company c")
	public List<CompanyDto> getAllCompanies();
	
	@Modifying
	@Query("update Company c set c.phoneNumber=?1, c.address=?2 where c.id=?3")
	public void updateCompany(String phoneNumber, String address, long id);
	
	public boolean existsByName(String name);	
}
