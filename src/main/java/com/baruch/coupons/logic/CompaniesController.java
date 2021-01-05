package com.baruch.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.dto.CompanyDto;
import com.baruch.coupons.entities.Company;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.repository.ICompaniesRepository;

@Controller
public class CompaniesController {

	@Autowired
	private ICompaniesRepository repository;

	public long createCompany(CompanyDto companyDto) throws ApplicationException {
		validateCreateCompany(companyDto);
		Company company = new Company(companyDto);
		try {
			repository.save(company);
			return company.getId();
		}
		catch(Exception e) {
			throw new ApplicationException("createCompany() failed for " + company.toString(),ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public void deleteCompany(long companyID) throws ApplicationException{
		repository.deleteById(companyID);
	}

	public CompanyDto getCompany(long companyID) throws ApplicationException{
		validateCompanyID(companyID);
		try {
			return repository.getCompany(companyID);
		}
		catch(Exception e) {
			throw new ApplicationException("getCompany() failed for id = " + companyID,ErrorTypes.GENERAL_ERROR,e);
		}
	}

	public List<CompanyDto> getAllCompanies() throws ApplicationException{
		try {
			return repository.getAllCompanies();
		}
		catch(Exception e) {
			throw new ApplicationException("getAllCompanies() failed",ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	//Only Adim users have access to this method.
	@Transactional
	public void updateCompany(CompanyDto companyDto) throws ApplicationException{
		validateUpdateCompany(companyDto);
		long id = companyDto.getId();
		String phoneNumber = companyDto.getPhoneNumber();
		String address = companyDto.getAddress();

		try {
			repository.updateCompany(phoneNumber, address, id);
		}
		catch(Exception e) {
			throw new ApplicationException("updateCompany() failed",ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	
	//PRIVATE-METHODS
	
	 Company getCompanyEntity(long companyID) throws ApplicationException{
		validateCompanyID(companyID);
		try {
			return repository.findById(companyID).get();
		}
		catch(Exception e) {
			throw new ApplicationException("getCompany() failed for id = " + companyID,ErrorTypes.GENERAL_ERROR,e);
		}
	}
	
	 void validateCompanyID(long companyID) throws ApplicationException{
		if( ! repository.existsById(companyID)) {
			throw new ApplicationException("CompaniesController.validateCompanyID() failed for ID: " + companyID, ErrorTypes.NO_COMPANY_ID);
		}
	}

	private void validateCreateCompany(CompanyDto companyDto) throws ApplicationException{
		String name = companyDto.getName();
		if(repository.existsByName(name)) {
			throw new ApplicationException(ErrorTypes.EXISTING_COMPANY_ERROR);
		}
		validatePhoneNumber(companyDto.getPhoneNumber());
		validateName(companyDto.getName());
		validateAddress(companyDto.getAddress());
	}
	
	private void validateUpdateCompany(CompanyDto companyDto) throws ApplicationException{
		validateCompanyID(companyDto.getId());
		validatePhoneNumber(companyDto.getPhoneNumber());
		validateAddress(companyDto.getAddress());
	}

	private void validatePhoneNumber(String phoneNumber) throws ApplicationException{
		if(phoneNumber == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_PHONENUMBER_ERROR);
		}
		if(phoneNumber.length()<10) {
			throw new ApplicationException(ErrorTypes.INVALID_PHONENUMBER_ERROR);
		}
	}

	private void validateAddress(String address) throws ApplicationException{
		if(address == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_ADDRESS_ERROR);
		}
		if(address.length()<2) {
			throw new ApplicationException(ErrorTypes.INAVLID_ADDRESS_ERROR);
		}
	}

	private void validateName(String name) throws ApplicationException{
		if(name == null) {
			throw new ApplicationException(ErrorTypes.EMPTY_ADDRESS_ERROR);
		}
		if(name.length()<2) {
			throw new ApplicationException(ErrorTypes.INAVLID_ADDRESS_ERROR);
		}
	}

}
