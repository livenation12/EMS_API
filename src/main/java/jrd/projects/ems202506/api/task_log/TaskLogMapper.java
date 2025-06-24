package jrd.projects.ems202506.api.task_log;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.task_log.dto.TaskLogDto;

@Mapper
public interface TaskLogMapper {
	TaskLogMapper INSTANCE = Mappers.getMapper(TaskLogMapper.class);

	TaskLogDto toDto(TaskLog taskLog);

	List<TaskLogDto> toDtoList(List<TaskLog> taskLogs);
}
