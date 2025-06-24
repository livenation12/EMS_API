package jrd.projects.ems202506.api.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jrd.projects.ems202506.api.employee.dto.EmployeeMinDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusDto;
import lombok.Data;

@Data
public class TaskDto {

	private Long id;

	private String title;
	private String description;

	private Float position;

	private TaskStatusDto status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	private EmployeeMinDto assignedTo;

	private EmployeeMinDto assignedBy;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDate dueDate;
}
