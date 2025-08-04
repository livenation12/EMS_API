package jrd.projects.ems202506.api.user.dto;

import jrd.projects.ems202506.api.employee.dto.EmployeeMinDto;
import lombok.Data;

@Data
public class UserMinDto {

	private String id;

	private String email;

	private EmployeeMinDto employee;
}
