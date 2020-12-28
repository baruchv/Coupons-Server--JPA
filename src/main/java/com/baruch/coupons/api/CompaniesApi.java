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

import com.baruch.coupons.dto.CompanyDto;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.CompaniesController;

@RestController
@RequestMapping("/companies")
public class CompaniesApi {
	
	@Autowired
	private CompaniesController con;
	
	@PostMapping
	public long createCompany(@RequestBody CompanyDto companyDto) throws ApplicationException{
		return con.createCompany(companyDto);
	}
	
	@PutMapping
	public void updateCompany(@RequestBody CompanyDto companyDto) throws ApplicationException{
		con.updateCompany(companyDto);
	}
	
	@GetMapping("/{companyID}")
	public CompanyDto getCompany(@PathVariable("companyID") long id) throws ApplicationException{
		return con.getCompany(id);
	}
	
	@GetMapping
	public List<CompanyDto> getAllCompanies() throws ApplicationException{
		return con.getAllCompanies();
	}
	
	@DeleteMapping
	public void deleteCompany(long id) throws ApplicationException{
		con.deleteCompany(id);
	}
	
	
	
}
