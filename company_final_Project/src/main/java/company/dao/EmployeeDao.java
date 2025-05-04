package company.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import company.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);


}
