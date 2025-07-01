package jrd.projects.ems202506.api.schedule.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDto {
	private Long id;

	private String title;

	private String description;

	private LocalDateTime start;

	private LocalDateTime end;
}
