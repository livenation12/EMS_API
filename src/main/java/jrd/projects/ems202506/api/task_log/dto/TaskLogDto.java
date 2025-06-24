package jrd.projects.ems202506.api.task_log.dto;

import java.time.LocalDateTime;

import jrd.projects.ems202506.api.auth.User;
import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.task.Task;
import lombok.Data;

@Data
public class TaskLogDto {
	private Long id;

	private Task task;

	private ActionType type;

	private User createdBy;

	private LocalDateTime createdAt;
}
