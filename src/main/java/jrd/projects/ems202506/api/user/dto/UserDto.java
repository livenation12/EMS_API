package jrd.projects.ems202506.api.user.dto;

import java.util.Set;

import jrd.projects.ems202506.api.employee.dto.EmployeeDto;
import jrd.projects.ems202506.api.employee.dto.EmployeeMinDto;
import jrd.projects.ems202506.api.role.Role;
import lombok.Data;

@Data
public class UserDto {

	private Long id;

	private String email;

	private EmployeeDto employee;

	private EmployeeMinDto supervisor;

	private Set<Role> roles;
}
