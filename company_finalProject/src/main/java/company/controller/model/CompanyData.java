package company.controller.model;

import java.util.HashSet;
import java.util.Set;

import company.entity.Company;
import company.entity.Department;
import company.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyData {
	//data transfer object
		private Long companyId;
		private String companyName;
		private String companyEmail;
		private String companyAddress;
		private String city;
		private String state;
		private String zip;
		private Long phone;
		private Set<EmployeeData> employees = new HashSet<>();
		
		//get fields (constructor)
		public CompanyData(Company company) {
			companyId = company.getCompanyId();
			companyName = company.getCompanyName();
			companyEmail = company.getCompanyEmail();
			companyAddress = company.getCompanyAddress();
			city = company.getCity();
			state = company.getState();
			zip = company.getZip();
			phone = company.getPhone();
			
			for(Employee employee : company.getEmployees()) {
				employees.add(new EmployeeData(employee));
			}
		}
		
		 		//inner class
		
		@Data
		@NoArgsConstructor
		public static class EmployeeData{
			private Long employeeId;
			private String employeeName;
			private int age;
			private String gender;
			private String position;
			private String qualification;
			private String address;
			private String email;
			private Set<String> departments = new HashSet<>();
			
			//constructor that takes Company objects
			public EmployeeData(Employee employee) {
				employeeId = employee.getEmployeeId();
				employeeName = employee.getEmployeeName();
				age = employee.getAge();
				gender = employee.getGender();
				position = employee.getPosition();
				qualification = employee.getQualification();
				address = employee.getAddress();
				email = employee.getEmail();
				
				for(Department department : employee.getDepartments()) {
					departments.add(department.getDepartment());
			}
		}
	}
}
	

	
		
		