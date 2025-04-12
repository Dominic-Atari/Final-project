package company.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import company.controller.model.CompanyData;
import company.dao.CompanyDao;
import company.entity.Company;

@Service
public class CompanyService {


	@Autowired
	private CompanyDao companyDao;

	//retrieving data
	@Transactional(readOnly = false)
	public CompanyData savaCompany(CompanyData companyData) {
		Long companyId = companyData.getCompanyId();
		Company company = findOrCreateCompany(companyId, companyData.getCompanyEmail());
		
		setFieldsInCompany(company, companyData);
		return new CompanyData(companyDao.save(company));
	}

	private void setFieldsInCompany(Company company, CompanyData companyData) {
		//don't set Id because it is being handled by java.
		company.setCompanyName(companyData.getCompanyName());
		company.setCompanyEmail(companyData.getCompanyEmail());
		company.setCompanyAddress(companyData.getCompanyAddress());
		company.setCity(companyData.getCity());
		company.setState(companyData.getState());
		company.setZip(companyData.getZip());
		company.setPhone(companyData.getPhone());
		
	}
	
	private Company findOrCreateCompany(Long companyId, String companyEmail) {
		Company company;
		
		if(Objects.isNull(companyId)) {
			Optional<Company> opCompany = companyDao.findByCompanyEmail(companyEmail);
		
			if(opCompany.isPresent()) {
				throw new DuplicateKeyException("Contributor with email " + companyEmail + "already exist.");

			}
			
			company = new Company();
			
		}else {
			company = findCompanyById(companyId);
		}
		return company;
	}

	private Company findCompanyById(Long companyId) {
		return companyDao.findById(companyId)
				.orElseThrow(() -> new NoSuchElementException("Company with ID=" + companyId + "was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<CompanyData> retrieveAllCompanies() {

		//@formatter:off
		return companyDao.findAll()
				.stream()
				.map(CompanyData::new)
				.toList();
		//@formatter:on
	}

	@Transactional(readOnly = true)
	public CompanyData retrieveCompanyById(Long companyId) {
		Company company = findCompanyById(companyId);
		return new CompanyData(company);
	}

	/*
	 * @Transactional(readOnly = false) private Company findCompanyById(Long
	 * companyId) { Company company = findCompanyById(companyId);
	 * companyDao.delete(company);
	 * 
	 * }
	 */

	// deleting company

	@Transactional(readOnly = false)
	public void deleteCompanyById(Long companyId) {
		Company company = findCompanyById(companyId);
		companyDao.delete(company);
	}
}
