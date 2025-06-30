package jrd.projects.ems202506.api.task_log.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jrd.projects.ems202506.api.auth.dto.UserEmployeeDto;
import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.task.dto.TaskMinDto;
import lombok.Data;

@Data
public class TaskLogDto {
	private Long id;

	private TaskMinDto task;

	private ActionType type;

	private UserEmployeeDto createdBy;

	@JsonFormat(pattern="MMM-dd-yyyy hh:mm:ss")
	private LocalDateTime createdAt;
}
