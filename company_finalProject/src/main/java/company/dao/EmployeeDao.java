package company.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import company.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
