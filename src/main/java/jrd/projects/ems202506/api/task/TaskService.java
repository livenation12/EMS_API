package jrd.projects.ems202506.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;

@Service
public class TaskService {

	@Autowired
	private TaskRepo taskRepo;

	public TaskDto createTask(TaskRequestDto request) {
		Task task = new Task();


		return TaskMapper.INSTANCE.toDto(taskRepo.save(task));
	}
}
