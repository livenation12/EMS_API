package jrd.projects.ems202506.api.schedule;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.employee.EmployeeMapper;
import jrd.projects.ems202506.api.schedule.dto.ScheduleDto;
import jrd.projects.ems202506.api.schedule.dto.ScheduleRequestDto;

@Mapper(uses = { EmployeeMapper.class })
public interface ScheduleMapper extends BaseMapper<ScheduleRequestDto, ScheduleDto, Schedule> {

	ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

	@Override
	List<ScheduleDto> toDtoList(List<Schedule> schedules);
}
