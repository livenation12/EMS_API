package jrd.projects.ems202506.api.task_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusRequestDto;

@RestController
@RequestMapping("/api/tasks/status")
public class TaskStatusController {


	@Autowired
	private TaskStatusService taskStatusService;

	@PostMapping
	public ApiResponse<TaskStatusDto> createTaskStatus(@RequestBody @Valid TaskStatusRequestDto request){
		TaskStatusDto newTaskStatus = taskStatusService.createTaskStatus(request);
		return ApiResponse.success(newTaskStatus, "Task status created sucessfuly");
	}
}
