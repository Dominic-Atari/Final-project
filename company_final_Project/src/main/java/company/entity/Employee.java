package company.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
	//@Formatter:off
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String employeeName;
	private int age;
	private String gender;
	private String position;
	private String qualification;
	private String address;
	
	@Column(unique = true)
	private String email;
	//@Formatter:on
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL) //bidirectional
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	@ManyToMany(cascade = CascadeType.PERSIST) //this is the owner
	@JoinTable(
			name = "employee_department",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id")

	)
	private Set<Department> departments = new HashSet<>();
}
