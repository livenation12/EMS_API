package jrd.projects.ems202506.api.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmployeeRequestDto{

	private final String requiredMsg = " is required";

	@NotBlank(message = "First name" + requiredMsg)
	private String firstName;
	@NotBlank(message = "Last name" + requiredMsg)
	private String lastName;
	private String middleName;
	private String office;
	private String jobTitle;

	@Email(message = "Email pattern is wrong")
	@NotBlank(message = "Email" + requiredMsg)
	private String email;
}
