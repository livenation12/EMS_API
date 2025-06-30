package jrd.projects.ems202506.api.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jrd.projects.ems202506.api.common.Utils;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee.EmployeeRepo;
import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;
import jrd.projects.ems202506.api.task_log.TaskLogService;
import jrd.projects.ems202506.api.task_status.TaskStatus;
import jrd.projects.ems202506.api.task_status.TaskStatusMapper;
import jrd.projects.ems202506.api.task_status.TaskStatusRepo;
import jrd.projects.ems202506.api.task_status.dto.KanbanColumnDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepo taskRepo;

	private final EmployeeRepo employeeRepo;

	private final TaskStatusRepo taskStatusRepo;

	private final TaskLogService taskLogService;

	@Transactional
	public TaskDto createTask(TaskRequestDto request) {

		TaskStatus defaultStatus = taskStatusRepo.findByIsDefault(true)
				.orElseThrow(() -> new ApiException("Provide a default task status first", HttpStatus.BAD_REQUEST));
		Employee employeeToAssign = employeeRepo.findById(request.getAssignedToId())
				.orElseThrow(() -> new ApiException("To assign employee not found", HttpStatus.NOT_FOUND));
		Task task = TaskMapper.INSTANCE.toEntity(request);
		task.setAssignedBy(Utils.getAuthUser().getEmployee());
		task.setAssignedTo(employeeToAssign);
		task.setStatus(defaultStatus);
		Float maxPosition = taskRepo.findMaxPosition(defaultStatus.getId(), employeeToAssign.getId());
		Float nextPosition = (maxPosition != null ? maxPosition : 0f) + 1;
		task.setPosition(nextPosition);
		taskLogService.createLog(task, ActionType.CREATED);
		return TaskMapper.INSTANCE.toDto(taskRepo.save(task));
	}

	@Transactional
	public List<KanbanColumnDto> readUserKanbanList() {
		Employee userEmployee = Utils.getAuthUser().getEmployee();
		if (userEmployee == null) {
			throw new ApiException("User doesn't have employee details", HttpStatus.FORBIDDEN);
		}

		List<Task> taskList = taskRepo.findAllByAssignedTo(userEmployee);
		List<TaskStatus> statusList = taskStatusRepo.findAll();

		// Construction of kanban data
		Map<Long, List<TaskDto>> tasksByStatus = taskList.stream().collect(Collectors.groupingBy(
				task -> task.getStatus().getId(), Collectors.mapping(TaskMapper.INSTANCE::toDto, Collectors.toList())));

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
		Task task = taskRepo.findById(request.getId())
				.orElseThrow(() -> new ApiException("Task not found", HttpStatus.NOT_FOUND));
		TaskStatus taskStatus = taskStatusRepo.findById(request.getStatusId())
				.orElseThrow(() -> new ApiException("Task status not found", HttpStatus.BAD_REQUEST));
		task.setStatus(taskStatus);
		task.setPosition(request.getPosition());
		if (request.getDescription() != null) {
			task.setDescription(request.getDescription());
		}
		if (request.getTitle() != null) {
			task.setTitle(request.getTitle());
		}
		taskLogService.createLog(task, ActionType.UPDATED);
		taskRepo.save(task);
		return TaskMapper.INSTANCE.toDto(task);
	}

}
