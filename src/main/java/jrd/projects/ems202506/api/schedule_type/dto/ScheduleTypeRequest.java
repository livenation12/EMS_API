package jrd.projects.ems202506.api.schedule_type.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ScheduleTypeRequest {

	@NotBlank(message = "Label is required")
	private String label;

	private String colorCode;

}
