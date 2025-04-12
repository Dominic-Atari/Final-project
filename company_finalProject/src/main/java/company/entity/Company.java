package company.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	
	private String companyName;
	
	@Column(unique = true)
	private String companyEmail; // added
	private String companyAddress;
	private String city;
	private String state;
	private String zip;
	private Long phone;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL) //bidirectional
	private Set<Employee> employees = new HashSet<>();
}
