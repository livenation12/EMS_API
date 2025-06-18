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
import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.task.dto.KanbanColumnDto;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;
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
			return kanbanColumn;
		}).toList();

		return kanban;
	}

}
