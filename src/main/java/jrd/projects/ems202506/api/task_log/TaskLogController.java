package jrd.projects.ems202506.api.task_log;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.task_log.dto.TaskLogDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks/logs")
@RequiredArgsConstructor
public class TaskLogController {

	private final TaskLogService taskLogService;

	@GetMapping("/latest")
	public ApiResponse<Page<TaskLogDto>> readLatestTaskLogListByUser(@RequestParam(defaultValue = "0") Integer pageNumber){
		return ApiResponse.success(taskLogService.readLatestByUser(pageNumber));
	}
}
