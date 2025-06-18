package jrd.projects.ems202506.api.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.task_status.TaskStatus;
import lombok.Data;

@Data
public class TaskDto {

	private Long id;

	private String title;
	private String description;

	private TaskStatus status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	private Employee assignedTo;

	private Employee assignedBy;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDate dueDate;
}
