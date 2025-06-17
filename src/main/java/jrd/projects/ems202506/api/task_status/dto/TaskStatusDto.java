package jrd.projects.ems202506.api.task_status.dto;

import lombok.Data;

@Data
public class TaskStatusDto {
	private Long id;

	private String label; // e.g. "To Do", "In Progress", "Done"
	private Integer order; // For ordering columns
	private String colorCode; // Optional for frontend use
}
