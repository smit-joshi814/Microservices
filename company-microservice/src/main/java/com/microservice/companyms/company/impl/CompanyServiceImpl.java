package com.microservice.companyms.company.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.companyms.company.Company;
import com.microservice.companyms.company.CompanyRepository;
import com.microservice.companyms.company.CompanyService;
import com.microservice.companyms.company.clients.ReviewClient;
import com.microservice.companyms.company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;


@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ReviewClient reviewClient;

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	@Override
	public Company getCompanyById(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company newCompany, Long companyId) {
		companyRepository.findById(companyId).ifPresent(company->{
			if(Objects.nonNull(newCompany.getName()) && !newCompany.getName().equalsIgnoreCase("")) {
				company.setName(newCompany.getName());
			}
			if(Objects.nonNull(newCompany.getDescription()) && !newCompany.getDescription().equalsIgnoreCase("")) {
				company.setDescription(newCompany.getDescription());
			}
			companyRepository.save(company);
		});
	}

	@Override
	public Boolean deleteCompany(Long companyId) {
		try {
			companyRepository.deleteById(companyId);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		Company company= companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(()-> new NotFoundException("Company not found : "+reviewMessage.getCompanyId()));
		
		Double avaregaeRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(avaregaeRating);
		companyRepository.save(company);
		
	}

}
