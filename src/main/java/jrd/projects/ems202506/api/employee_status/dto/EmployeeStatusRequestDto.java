package jrd.projects.ems202506.api.employee_status.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class EmployeeStatusRequestDto {

	@NotEmpty
	private List<Long> employeeIds;

	private String task;

	private Long statusId;
}
