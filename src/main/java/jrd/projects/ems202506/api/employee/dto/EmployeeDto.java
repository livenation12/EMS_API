package jrd.projects.ems202506.api.employee.dto;

import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String office;
	private String jobTitle;
}
