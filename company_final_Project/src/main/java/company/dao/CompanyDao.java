package company.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import company.entity.Company;

public interface CompanyDao extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyEmail(String companyEmail);

}
