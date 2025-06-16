package jrd.projects.ems202506.api.employee_status_type.dto;

import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusType;
import lombok.Data;

@Data
public class EmployeeStatusTypeDto {
	private Long id;
	private String label;
	private String colorCode;

	public EmployeeStatusTypeDto(EmployeeStatusType statusType) {
		this.id = statusType.getId();
		this.label = statusType.getLabel();
		this.colorCode = statusType.getColorCode();
	}
}
