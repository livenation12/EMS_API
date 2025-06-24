package jrd.projects.ems202506.api.task;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.employee.EmployeeMapper;
import jrd.projects.ems202506.api.task.dto.TaskDto;
import jrd.projects.ems202506.api.task.dto.TaskRequestDto;
import jrd.projects.ems202506.api.task_status.TaskStatusMapper;

@Mapper(uses = {EmployeeMapper.class, TaskStatusMapper.class})
public interface TaskMapper extends BaseMapper<TaskRequestDto, TaskDto, Task> {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	@Override
	TaskDto toDto(Task entity);

	List<TaskDto> toDtoList(List<Task> tasks);

	@Override
	Task toEntity(TaskRequestDto request);

}
