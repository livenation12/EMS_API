package jrd.projects.ems202506.api.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.common.Utils;
import jrd.projects.ems202506.api.exception.ApiException;
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

		LocalDateTime dateNow = LocalDateTime.now();

		/** Start Validations **/
		if(request.getEndDate().isBefore(request.getStartDate())) {
			throw new FieldValidationException("End date should not be greater than Start date", "endDate");
		}
		if(request.getStartDate().isBefore(dateNow) || request.getEndDate().isBefore(dateNow)) {
			throw new ApiException("Dates should not be less then today", HttpStatus.BAD_REQUEST);
		}
		/** End Validations **/

		Schedule schedule = ScheduleMapper.INSTANCE.toEntity(request);
		schedule.setCreatedBy(Utils.getAuthUser());
		return ScheduleMapper.INSTANCE.toDto(scheduleRepo.save(schedule));
	}

	public List<ScheduleDto> readByRange(DateRangeFilter range){
		List<Schedule> scheduleList = scheduleRepo.findByStartDateBetween(range.getStartDate(), range.getEndDate());
		return ScheduleMapper.INSTANCE.toDtoList(scheduleList);
	}
}
