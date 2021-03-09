package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.datapresentation.dataInterfaces.ICompanyDataObject;
import com.baruch.coupons.entities.Company;

@Repository
public interface ICompaniesRepository extends CrudRepository<Company, Long>{

	@Query("select new com.baruch.coupons.datapresentation.company.CompanyFullData(c.id, c.name, c.address, c.phoneNumber) from Company c where c.id = ?1")
	public ICompanyDataObject getCompany(long companyID);

	@Query("select new com.baruch.coupons.datapresentation.company.CompanyBasicData(c.id, c.name) from Company c")
	public List<ICompanyDataObject> getAllCompanies();
	
	@Modifying
	@Query("update Company c set c.phoneNumber=?1 where c.id=?2")
	public void updateCompanyPhoneNumber(String phoneNumber, long companyID);
	
	@Modifying
	@Query("update Company c set c.address=?1 where c.id=?2")
	public void updateCompanyAddress(String address, long companyID);

	public boolean existsByName(String name);	
}
