package company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import company.controller.model.CompanyData;
import company.controller.model.CompanyData.EmployeeData;
import company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	// create company
	@PostMapping("/company")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CompanyData createCompany(@RequestBody CompanyData companyData) {
		log.info("Creating Company {}", companyData);
		return companyService.savaCompany(companyData);
	}
	
	//Update company service
	
	@PutMapping("/company/{companyId}")
	public CompanyData updateCompany(@PathVariable Long companyId,
			@RequestBody CompanyData companyData ) {
		companyData.setCompanyId(companyId);
		log.info("Update company {}", companyData);
		return companyService.savaCompany(companyData);
		
	}
	
	//retrieve all company (Get)
	
	@GetMapping("/company")
	public List<CompanyData> retrieveAllCompanies() {
		log.info("Retrie all Company data by ID");
		return companyService.retrieveAllCompanies();
	}
	
	//retrieve company by ID.
	
	@GetMapping("/company/{companyId}")
	public CompanyData retrieveCompanyById(@PathVariable Long companyId) {
		log.info("Retrieving company by ID= {}", companyId);
		return companyService.retrieveCompanyById(companyId);
	}
	
	//Delete by ID
	
		@DeleteMapping("/company/{companyId}")
		public Map<String, String> deleteCompanyById(@PathVariable Long companyId) {
			log.info("Deleting company by ID={}", companyId);

			companyService.deleteCompanyById(companyId);

			return Map.of("message", "Deletion of company by ID= " + companyId + "was successfull.");
		}

		// delete a company

		@DeleteMapping("/company")
		public void deleteAllCompany() {
			log.info("Attempting to delete all company");
			throw new UnsupportedOperationException("Deleting all company is not allowed.");
		}
		//-------------------------------------------------------------------------
		
		//Employee methods
		
		@PostMapping("/company/{companyid}/employee")
		@ResponseStatus(code = HttpStatus.CREATED)
		public EmployeeData insertEmployee(@PathVariable Long companyId, 
				@RequestBody EmployeeData employeeData) {
			
			log.info("creating employee {} for company with ID={}", companyId, employeeData);
			
			return companyService.saveEmployee(companyId, employeeData);
		}
}
