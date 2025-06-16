package jrd.projects.ems202506.api.employee_status;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusType;
import lombok.Data;

@Data
@Entity
public class EmployeeStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	private EmployeeStatusType status;

	private String task;

	private LocalDateTime timestamp;

	private String note;
}
