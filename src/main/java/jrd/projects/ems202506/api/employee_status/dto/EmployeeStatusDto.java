package jrd.projects.ems202506.api.employee_status.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jrd.projects.ems202506.api.employee_status.EmployeeStatus;
import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeStatusDto {

	private Long id;

	private String employeeName;

	private Long employeeId;

	private String task;

	private EmployeeStatusType status;

	@JsonFormat(pattern="MMM-dd-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public EmployeeStatusDto(EmployeeStatus employeeStatus) {
		this.employeeName = employeeStatus.getEmployee().getFullName();
		this.id = employeeStatus.getId();
		this.employeeId = employeeStatus.getEmployee().getId();
		this.task = employeeStatus.getTask();
		this.status = employeeStatus.getStatus();
		this.timestamp = employeeStatus.getTimestamp();
	}
}
