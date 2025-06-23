package jrd.projects.ems202506.api.task.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDto {
	private Long id;

	@NotBlank(message = "Title is required")
	private String title;
	private String description;

	private Long statusId; // e.g. ASSIGNED, IN_PROGRESS, DONE

	private Long assignedToId;

	private LocalDate dueDate;

	private Float position;
}
