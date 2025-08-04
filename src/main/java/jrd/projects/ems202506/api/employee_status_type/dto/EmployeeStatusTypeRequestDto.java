package jrd.projects.ems202506.api.employee_status_type.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeStatusTypeRequestDto {

	@NotBlank(message = "Label is required")
	private String label;

	private String colorCode;
}
