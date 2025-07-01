package jrd.projects.ems202506.api.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.task_status.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;

	private Float position;

	@ManyToOne
	private TaskStatus status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	@ManyToOne
	private Employee assignedTo;

	@ManyToOne
	private Employee assignedBy;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private LocalDate dueDate;
}
