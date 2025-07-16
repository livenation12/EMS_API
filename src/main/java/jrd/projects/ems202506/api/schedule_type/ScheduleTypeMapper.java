package jrd.projects.ems202506.api.schedule_type;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeDto;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeRequest;

@Mapper
public interface ScheduleTypeMapper extends BaseMapper<ScheduleTypeRequest, ScheduleTypeDto, ScheduleType> {

	ScheduleTypeMapper INSTANCE = Mappers.getMapper(ScheduleTypeMapper.class);

}
