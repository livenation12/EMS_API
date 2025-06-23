package jrd.projects.ems202506.api.task_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusRequestDto;

@Service
public class TaskStatusService {

	@Autowired
	private TaskStatusRepo taskStatusRepo;

	@Transactional
	public TaskStatusDto createTaskStatus(TaskStatusRequestDto request) {
		if(request.getIsDefault()) {
			taskStatusRepo.setNewDefault();
		}
		Float max = taskStatusRepo.findMaxPosition();
		Float nextPosition = max != null ? max + 1 : 1;
		TaskStatus taskStatus = TaskStatusMapper.INSTANCE.toEntity(request);
		taskStatus.setPosition(nextPosition);
		return TaskStatusMapper.INSTANCE.toDto(taskStatusRepo.save(taskStatus));
	}

}
