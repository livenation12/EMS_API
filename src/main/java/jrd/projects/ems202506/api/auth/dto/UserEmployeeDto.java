package jrd.projects.ems202506.api.auth.dto;

import jrd.projects.ems202506.api.employee.dto.EmployeeMinDto;
import lombok.Data;

@Data
public class UserEmployeeDto {

	private Long id;
	private EmployeeMinDto employee;
}
