package jrd.projects.ems202506.api.task_log;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.task_log.dto.TaskLogDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks/logs")
@RequiredArgsConstructor
public class TaskLogController {

	private final TaskLogService taskLogService;

	public ApiResponse<List<TaskLogDto>> readAllTaskLogList(){
		return ApiResponse.success(taskLogService.readAll());
	}
}
