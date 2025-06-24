package jrd.projects.ems202506.api.task_status;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.task.Task;
import jrd.projects.ems202506.api.task.TaskMapper;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task_status.dto.KanbanColumnDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusDto;
import jrd.projects.ems202506.api.task_status.dto.TaskStatusRequestDto;

@Mapper(uses = { TaskMapper.class })
public interface TaskStatusMapper {

	TaskStatusMapper INSTANCE = Mappers.getMapper(TaskStatusMapper.class);

	TaskStatusDto toDto(TaskStatus entity);

	List<TaskDto> toDtoList(List<Task> tasks);

	TaskStatus toEntity(TaskStatusRequestDto request);

	KanbanColumnDto toKanbanColumnDto(TaskStatus status);

}
