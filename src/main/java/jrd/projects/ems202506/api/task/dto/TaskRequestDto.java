package jrd.projects.ems202506.api.task.dto;

import java.time.LocalDate;

import jrd.projects.ems202506.api.task_status.TaskStatus;
import lombok.Data;

@Data
public class TaskRequestDto {
	private Long id;

	private String title;
	private String description;

	private TaskStatus status; // e.g. ASSIGNED, IN_PROGRESS, DONE

	private Long assignedToId;

	private LocalDate dueDate;
}
