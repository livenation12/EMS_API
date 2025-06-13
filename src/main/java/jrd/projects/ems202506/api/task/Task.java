package jrd.projects.ems202506.api.task;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jrd.projects.ems202506.api.employee.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task {
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private String description;

	private String status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	@ManyToOne
	private Employee assignedTo;

	@ManyToOne
	private Employee assignedBy;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


}
