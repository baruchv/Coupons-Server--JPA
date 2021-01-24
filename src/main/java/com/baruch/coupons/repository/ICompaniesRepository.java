package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dataInterfaces.ICompanyDataObject;
import com.baruch.coupons.entities.Company;

@Repository
public interface ICompaniesRepository extends CrudRepository<Company, Long>{

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CompanyFullData(c.id, c.name, c.address, c.phoneNumber) from Company c where c.id = ?1")
	public ICompanyDataObject getCompany(long companyID);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CompanyBasicData(c.id, c.name) from Company c")
	public List<ICompanyDataObject> getAllCompanies();
	
	@Modifying
	@Query("update Company c set c.phoneNumber=?1, c.address=?2 where c.id=?3")
	public void updateCompany(String phoneNumber, String address, long companyID);
	
	public boolean existsByName(String name);	
}
