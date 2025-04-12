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
	
	//constructor to use in test
	
	
	  public CompanyData(Long companyId, String companyName, String companyAddress,
	  String city, String state, String zip, Long phone) {
	  
	  this.companyId = companyId; this.companyName = companyName;
	  this.companyAddress = companyAddress; this.city = city; this.state = state;
	  this.zip = zip; this.phone = phone; 
	  }
	 
	
	
	// converts from company data object to company
	
	  public Company toCompany() { Company company = new Company();
	  
	  //set fields company.setCompanyId(companyId);
	  company.setCompanyName(companyName);
	  company.setCompanyAddress(companyAddress); company.setCity(city);
	  company.setState(state); company.setZip(zip); company.setPhone(phone);
	  
	  for(EmployeeData employeeData : employees) {
	  company.getEmployees().add(employeeData.toEmployee()); }
	  
	  return company; }
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
		private Set<DepartmentData> departments = new HashSet<>();
		
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
				departments.add(new DepartmentData(department));
			}
		}
		
		//toEmployees method
		public Employee toEmployee() {
			Employee employee = new Employee();
			
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(employeeName);
			employee.setAge(age);
			employee.setGender(gender);
			employee.setPosition(position);
			employee.setQualification(qualification);
			employee.setAddress(address);
			employee.setEmail(email);
			
			for(DepartmentData departmentData : departments) {
				employee.getDepartments().add(departmentData.toDepartment());
			}
			
			return employee;
		}
	}
	
	//inner class
	@Data
	@NoArgsConstructor
	public static class DepartmentData{
		private Long departmentId;
		private String departmentName;
		private Set<EmployeeData> employees = new HashSet<>();
		
		//constructor
		
		public DepartmentData(Department department) {
			departmentId = department.getDepartmentId();
			departmentName = department.getDepartmentName();
			
			for(Employee employee : department.getEmployees()) {
				employees.add(new EmployeeData(employee));
			}
			
		}
		// toDepartment method
		public Department toDepartment() {
			Department department = new Department();
			department.setDepartmentId(departmentId);
			department.setDepartmentName(departmentName);
			
			for(EmployeeData employeeData : employees) {
				department.getEmployees().add(employeeData.toEmployee());
			}
			
			return department;
		
	}
}

}


