package company.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	private String departmentName;
	
	@EqualsAndHashCode.Exclude //this avoids recursion.
	@ToString.Exclude
	@ManyToMany(mappedBy = "departments")
	private Set<Employee> employees = new HashSet<>();
}
