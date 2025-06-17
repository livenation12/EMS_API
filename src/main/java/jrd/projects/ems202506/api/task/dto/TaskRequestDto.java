package jrd.projects.ems202506.api.task.dto;

import java.time.LocalDateTime;

import jrd.projects.ems202506.api.employee.Employee;
import lombok.Data;

@Data
public class TaskRequestDto {
	private Long id;

	private String title;
	private String description;

	private String status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	private Employee assignedTo;

	private Employee assignedBy;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime dueDate;
}
