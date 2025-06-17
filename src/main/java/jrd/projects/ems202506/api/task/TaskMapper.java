package jrd.projects.ems202506.api.task;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.task.dto.TaskDto;

@Mapper
public interface TaskMapper {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	TaskDto toDto(Task entity);

}
