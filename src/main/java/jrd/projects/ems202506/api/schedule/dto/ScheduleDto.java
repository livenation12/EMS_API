package jrd.projects.ems202506.api.schedule.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jrd.projects.ems202506.api.auth.dto.UserEmployeeDto;
import lombok.Data;

@Data
public class ScheduleDto {
	private Long id;

	private String title;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private UserEmployeeDto createdBy;

	@JsonFormat(pattern="MMM-dd-yyyy hh:mm:ss")
	private LocalDateTime createdAt;
}
