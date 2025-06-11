package jrd.projects.ems202506.api.employee;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String office;
	private String jobTitle;
	private String jobStatus;
	private String email;
	private LocalDate birthDate;
	private int age;

}
