package jrd.projects.ems202506.api.task;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;

@Mapper
public interface TaskMapper extends BaseMapper<TaskRequestDto, TaskDto, Task> {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	@Override
	TaskDto toDto(Task entity);

	@Override
	Task toEntity(TaskRequestDto request);

}
