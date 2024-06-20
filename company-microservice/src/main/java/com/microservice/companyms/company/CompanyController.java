package com.microservice.companyms.company;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<Company>> findAllCompanies() {
		return ResponseEntity.ok(companyService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Company> getComnpanyById(@PathVariable Long id){
		return ResponseEntity.ok(companyService.getCompanyById(id));
	}

	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return ResponseEntity.ok("Company Created Successfully");
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updateCopmany(@RequestBody Company company, @PathVariable Long id) {
		companyService.updateCompany(company, id);
		return ResponseEntity.ok("Copmany Updated Successfully");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		if (companyService.deleteCompany(id)) {
			return ResponseEntity.ok("Copmany Deleted Successfully");
		}
		return ResponseEntity.notFound().build();
	}

}
