package jrd.projects.ems202506.api.schedule.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScheduleRequestDto {

	@NotBlank(message = "Title is required")
	private String title;

	private String description;

	@NotNull(message = "Start date is required")
	private LocalDateTime startDate;

	@NotNull(message = "End date is required")
	private LocalDateTime endDate;

	private Set<Long> participantIds;
}
