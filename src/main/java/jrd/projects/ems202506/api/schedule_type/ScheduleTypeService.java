package jrd.projects.ems202506.api.schedule_type;

import java.util.List;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeDto;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleTypeService {

	private final ScheduleTypeRepo scheduleTypeRepo;

	public void create(ScheduleTypeRequest request) {
		ScheduleType schedType = ScheduleTypeMapper.INSTANCE.toEntity(request);
		schedType.setColorCode(request.getColorCode());
		scheduleTypeRepo.save(schedType);
	}

	public List<ScheduleTypeDto> readAll(){
		List<ScheduleType> schedTypeList = scheduleTypeRepo.findAll();
		return ScheduleTypeMapper.INSTANCE.toDtoList(schedTypeList);
	}

}
