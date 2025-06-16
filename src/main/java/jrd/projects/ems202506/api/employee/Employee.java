package jrd.projects.ems202506.api.employee;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	@Column(nullable = true)
	private String middleName;
	private String office;
	private String jobTitle;
	private String jobStatus;
	private String email;
	private LocalDate birthDate;

	@ManyToOne(fetch = FetchType.EAGER)
	private EmployeeStatusType status;

	@Transient
	private int age;

	public int getAge() {
		if(birthDate == null) {
			return 0;
		}
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

	public String getFullName() {
		if (middleName != null && !middleName.isEmpty()) {
			return firstName + " " + middleName.charAt(0) + ". " + lastName;
		}
		return firstName + " " + lastName;
	}

}
