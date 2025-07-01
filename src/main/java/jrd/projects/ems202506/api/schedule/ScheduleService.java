package jrd.projects.ems202506.api.schedule;

import java.util.List;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.exception.FieldValidationException;
import jrd.projects.ems202506.api.schedule.dto.DateRangeFilter;
import jrd.projects.ems202506.api.schedule.dto.ScheduleDto;
import jrd.projects.ems202506.api.schedule.dto.ScheduleRequestDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepo scheduleRepo;

	public ScheduleDto create(ScheduleRequestDto request) {
		if(request.getEndDate().isBefore(request.getStartDate())) {
			throw new FieldValidationException("End date should not be greater than Start date", "endDate");
		}
		Schedule schedule = ScheduleMapper.INSTANCE.toEntity(request);
		return ScheduleMapper.INSTANCE.toDto(scheduleRepo.save(schedule));
	}

	public List<ScheduleDto> readByRange(DateRangeFilter range){
		List<Schedule> scheduleList = scheduleRepo.findByStartDateBetween(range.getStartDate(), range.getEndDate());
		return ScheduleMapper.INSTANCE.toDtoList(scheduleList);
	}
}
