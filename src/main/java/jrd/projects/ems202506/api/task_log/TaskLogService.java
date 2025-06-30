package jrd.projects.ems202506.api.task_log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.common.Utils;
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
		log.setCreatedBy(Utils.getAuthUser());
		log.setTask(task);
		log.setType(type);
		taskLogRepo.save(log);
	}

	public Page<TaskLogDto> readLatestByUser(Integer pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<TaskLog> taskPage = taskLogRepo.findAllByCreatedBy(Utils.getAuthUser(), pageRequest);
		return taskPage.map(TaskLogMapper.INSTANCE::toDto);
	}
}

