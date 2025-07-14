package jrd.projects.ems202506.api.schedule;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.schedule.dto.ScheduleDto;
import jrd.projects.ems202506.api.schedule.dto.ScheduleRequestDto;

@Mapper
public interface ScheduleMapper extends BaseMapper<ScheduleRequestDto, ScheduleDto, Schedule> {

	ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
}
