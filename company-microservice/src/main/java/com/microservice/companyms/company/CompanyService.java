package com.microservice.companyms.company;

import java.util.List;

import com.microservice.companyms.company.dto.ReviewMessage;

public interface CompanyService {

	List<Company> findAll();
	
	Company getCompanyById(Long companyId);
	
	void createCompany(Company company);
	
	void updateCompany(Company company,Long companyId);
	
	Boolean deleteCompany(Long companyId);
	
	void updateCompanyRating(ReviewMessage reviewMessage);
}
