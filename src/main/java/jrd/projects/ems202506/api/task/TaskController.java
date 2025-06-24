package jrd.projects.ems202506.api.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;
import jrd.projects.ems202506.api.task_status.dto.KanbanColumnDto;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;


	@PostMapping
	public ApiResponse<TaskDto> createTask(@RequestBody @Valid TaskRequestDto request) {
		TaskDto task = taskService.createTask(request);
		return ApiResponse.success(task, "Task created successfully");
	}

	@GetMapping("/kanban")
	public ApiResponse<List<KanbanColumnDto>> readKanban(){
		List<KanbanColumnDto> kanbanList = taskService.readUserKanbanList();
		return ApiResponse.success(kanbanList);
	}

	@PatchMapping("/{id}")
	public ApiResponse<TaskDto> updateTask(@RequestBody TaskRequestDto request){
		TaskDto task = taskService.updateTask(request);
		return ApiResponse.success(task, "Task updated successfully");
	}



}
