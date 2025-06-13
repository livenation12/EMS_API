package jrd.projects.ems202506.api.employee_status;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jrd.projects.ems202506.api.employee.Employee;
import lombok.Data;

@Data
@Entity
public class EmployeeStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Employee employee;

	private String status;

	private String task;

	private LocalDateTime timestamp;

	private String updatedBy;
}
