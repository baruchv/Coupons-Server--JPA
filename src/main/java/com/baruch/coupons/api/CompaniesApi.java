package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.datapresentation.dataInterfaces.ICompanyDataObject;
import com.baruch.coupons.dto.CompanyDto;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.CompaniesController;

//As product conceptions - only Admin users are authorized for this api.

@RestController
@RequestMapping("/companies")
public class CompaniesApi {
	
	@Autowired
	private CompaniesController controller;
	
	@PostMapping
	public long createCompany(@RequestBody CompanyDto companyDto) throws ApplicationException{
		return controller.createCompany(companyDto);
	}
	
	@PutMapping("/{companyID}/phoneNumber")
	public void updateCompanyPhoneNumber(@PathVariable("companyID") long companyID, @RequestBody CompanyDto companyDto) throws ApplicationException{
		controller.updateCompanyPhoneNumber(companyDto.getPhoneNumber(), companyID);
	}

	@PutMapping("/{companyID}/address")
	public void updateCompanyAddress(@PathVariable("companyID") long companyID, @RequestBody CompanyDto companyDto)
			throws ApplicationException {
		controller.updateCompanyAddress(companyDto.getAddress(), companyID);
	}
	
	@GetMapping("/{companyID}")
	public ICompanyDataObject getCompany(@PathVariable("companyID") long companyID) throws ApplicationException{
		return controller.getCompany(companyID);
	}
	
	@GetMapping
	public List<ICompanyDataObject> getAllCompanies() throws ApplicationException{
		return controller.getAllCompanies();
	}
	
	@DeleteMapping("/{companyID}")
	public void deleteCompany(@PathVariable("companyID") long companyID) throws ApplicationException{
		controller.deleteCompany(companyID);
	}
	
}
