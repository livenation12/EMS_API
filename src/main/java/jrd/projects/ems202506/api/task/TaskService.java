package jrd.projects.ems202506.api.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jrd.projects.ems202506.api.common.Utils;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee.EmployeeRepo;
import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.task.dto.KanbanColumnDto;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;
import jrd.projects.ems202506.api.task_log.TaskLogService;
import jrd.projects.ems202506.api.task_status.TaskStatus;
import jrd.projects.ems202506.api.task_status.TaskStatusMapper;
import jrd.projects.ems202506.api.task_status.TaskStatusRepo;

@Service
public class TaskService {

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private TaskStatusRepo taskStatusRepo;

	@Autowired
	private TaskLogService taskLogService;


	@Transactional
	public TaskDto createTask(TaskRequestDto request) {
		TaskStatus defaultStatus = taskStatusRepo.findByIsDefault(true)
				.orElseThrow(() -> new ApiException("Provide a default task status first"
						, HttpStatus.BAD_REQUEST));
		Employee employeeToAssign = employeeRepo.findById(request.getAssignedToId())
				.orElseThrow(() -> new ApiException("To assign employee not found", HttpStatus.NOT_FOUND));
		Task task = TaskMapper.INSTANCE.toEntity(request);
		task.setAssignedBy(Utils.getAuthUser().getEmployee());
		task.setAssignedTo(employeeToAssign);
		task.setStatus(defaultStatus);
		return TaskMapper.INSTANCE.toDto(taskRepo.save(task));
	}

	@Transactional
	public List<KanbanColumnDto> readUserKanbanList(){
		Employee userEmployee = Utils.getAuthUser().getEmployee();
		if(userEmployee == null) {
			throw new ApiException("User doesn't have employee details", HttpStatus.FORBIDDEN);
		}
		List<Task> taskList = taskRepo.findAllByAssignedTo(userEmployee);
		List<TaskStatus> statusList = taskStatusRepo.findAll();
		//Construction of kanban data
		Map<Long, List<TaskDto>> tasksByStatus = taskList.stream().collect(Collectors.groupingBy(
				task -> task.getStatus().getId(), Collectors.mapping(TaskMapper.INSTANCE::toDto, Collectors.toList())
				));
		List<KanbanColumnDto> kanban = statusList.stream().map(status -> {
			List<TaskDto> tasks = tasksByStatus.getOrDefault(status.getId(), new ArrayList<>());
			KanbanColumnDto kanbanColumn = TaskStatusMapper.INSTANCE.toKanbanColumnDto(status);
			kanbanColumn.setTasks(tasks);
			kanbanColumn.setId(status.getId());
			return kanbanColumn;
		}).toList();

		return kanban;
	}

	public TaskDto updateTask(TaskRequestDto request) {
		System.out.println("statusss " + request.getStatusId());
		Task task = taskRepo.findById(request.getId()).orElseThrow(() -> new ApiException("Task not found", HttpStatus.NOT_FOUND));
		TaskStatus taskStatus = taskStatusRepo.findById(request.getStatusId()).orElseThrow(() -> new ApiException("Task status not found", HttpStatus.BAD_REQUEST));
		task.setStatus(taskStatus);
		task.setPosition(request.getPosition());
		task.setDescription(request.getDescription());
		task.setTitle(request.getTitle());
		taskLogService.createLog(task, ActionType.UPDATED);
		return TaskMapper.INSTANCE.toDto(task);
	}

}
