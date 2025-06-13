package jrd.projects.ems202506.api.employee_status.dto;

import lombok.Data;

@Data
public class EmployeeStatusDto {

	private Long employeeId;

	private String task;

	private String status;

}
