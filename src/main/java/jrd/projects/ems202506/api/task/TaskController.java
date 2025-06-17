package jrd.projects.ems202506.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	public ApiResponse<TaskDto> createTask(@RequestBody @Valid TaskRequestDto request) {
		TaskDto task = taskService.createTask(request);
		return ApiResponse.success(task, "Task created successfully");
	}

}
