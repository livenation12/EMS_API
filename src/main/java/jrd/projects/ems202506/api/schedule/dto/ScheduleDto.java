package jrd.projects.ems202506.api.schedule.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jrd.projects.ems202506.api.auth.dto.UserEmployeeDto;
import jrd.projects.ems202506.api.employee.dto.EmployeeMinDto;
import jrd.projects.ems202506.api.schedule_type.ScheduleType;
import lombok.Data;

@Data
public class ScheduleDto {
	private Long id;

	private String title;

	private String description;

	private LocalDateTime startDate;

	private ScheduleType type;

	private Set<EmployeeMinDto> participants;

	private LocalDateTime endDate;

	private UserEmployeeDto createdBy;

	@JsonFormat(pattern="MMM-dd-yyyy hh:mm:ss")
	private LocalDateTime createdAt;
}
