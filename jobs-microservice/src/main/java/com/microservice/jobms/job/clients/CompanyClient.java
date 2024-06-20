package com.microservice.jobms.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.jobms.job.external.Company;

@FeignClient(name = "COMPANY-MICROSERVICE")
public interface CompanyClient {
	@GetMapping("companies/{id}")
	Company getCompany(@PathVariable("id") Long companyId);
}