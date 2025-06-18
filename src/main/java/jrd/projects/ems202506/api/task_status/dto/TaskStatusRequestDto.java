package jrd.projects.ems202506.api.task_status.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskStatusRequestDto {

	@NotBlank(message = "Label is required")
	private String label; // e.g. "To Do", "In Progress", "Done"

	@NotBlank(message = "Color is required")
	private String colorCode; // Optional for frontend use

	private Boolean isDefault;
}
