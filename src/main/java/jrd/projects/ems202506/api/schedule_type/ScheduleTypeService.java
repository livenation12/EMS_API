package jrd.projects.ems202506.api.schedule_type;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleTypeService {

	private ScheduleTypeRepo scheduleTypeRepo;

	public void create(ScheduleTypeRequest request) {
		ScheduleType schedType = ScheduleTypeMapper.INSTANCE.toEntity(request);
		scheduleTypeRepo.save(schedType);
	}

}
