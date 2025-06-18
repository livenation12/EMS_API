package jrd.projects.ems202506.api.task_status;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.task.dto.KanbanColumnDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusRequestDto;

@Mapper
public interface TaskStatusMapper {

	TaskStatusMapper INSTANCE = Mappers.getMapper(TaskStatusMapper.class);


	TaskStatusDto toDto(TaskStatus entity);

	TaskStatus toEntity(TaskStatusRequestDto request);

	KanbanColumnDto toKanbanColumnDto(TaskStatus status);

}
