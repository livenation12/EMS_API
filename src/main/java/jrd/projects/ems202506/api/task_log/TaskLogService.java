package jrd.projects.ems202506.api.task_log;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.enums.ActionType;
import jrd.projects.ems202506.api.task.Task;

@Service
public class TaskLogService {


	public void createLog(Task task, ActionType type) {
		TaskLog log = new TaskLog();
		log.setTask(task);
		log.setType(type);
	}
}

