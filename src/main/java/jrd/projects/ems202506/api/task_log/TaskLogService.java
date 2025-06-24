package jrd.projects.ems202506.api.task_log;

import java.util.List;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.task.Task;
import jrd.projects.ems202506.api.task_log.dto.TaskLogDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskLogService {

	private final TaskLogRepo taskLogRepo;


	public void createLog(Task task, ActionType type) {
		TaskLog log = new TaskLog();
		log.setTask(task);
		log.setType(type);
		taskLogRepo.save(log);
	}

	public List<TaskLogDto> readAll(){
		return TaskLogMapper.INSTANCE.toDtoList(taskLogRepo.findAll());
	}
}

