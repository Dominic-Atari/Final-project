package company.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import company.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, Long> {

	

	Set<Department> findAllByDepartmentIn(Set<String> departments);

}
