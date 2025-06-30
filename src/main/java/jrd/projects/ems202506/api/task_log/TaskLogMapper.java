package jrd.projects.ems202506.api.task_log;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.auth.AuthMapper;
import jrd.projects.ems202506.api.task.TaskMapper;
import jrd.projects.ems202506.api.task_log.dto.TaskLogDto;

@Mapper(uses = { AuthMapper.class, TaskMapper.class })
public interface TaskLogMapper {
	TaskLogMapper INSTANCE = Mappers.getMapper(TaskLogMapper.class);

	TaskLogDto toDto(TaskLog taskLog);

	List<TaskLogDto> toDtoList(List<TaskLog> taskLogs);
}
