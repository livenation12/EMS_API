package jrd.projects.ems202506.api.schedule;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.common.Utils;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee.EmployeeRepo;
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

	private final EmployeeRepo employeeRepo;

	public ScheduleDto create(ScheduleRequestDto request) {

		//Date validation
		validateDateRange(request.getStartDate(), request.getEndDate());

		Schedule schedule = ScheduleMapper.INSTANCE.toEntity(request);
		schedule.setParticipants(findParticipantsFromRequest(request));
		schedule.setCreatedBy(Utils.getAuthUser());
		return ScheduleMapper.INSTANCE.toDto(scheduleRepo.save(schedule));
	}

	private Set<Employee> findParticipantsFromRequest(ScheduleRequestDto request){
		List<Employee> employees = employeeRepo.findAllById(request.getParticipantIds());
		return new HashSet<>(employees);
	}

	public List<ScheduleDto> readByRange(DateRangeFilter range) {
		List<Schedule> scheduleList = scheduleRepo.findByStartDateBetween(range.getStartDate(), range.getEndDate());
		return ScheduleMapper.INSTANCE.toDtoList(scheduleList);
	}

	public ScheduleDto updateById(Long id, ScheduleRequestDto request) {

		//Date validation
		validateDateRange(request.getStartDate(), request.getEndDate());

		Schedule schedule = scheduleRepo.findById(id).orElseThrow(() -> new ApiException("Schedule not found", HttpStatus.NOT_FOUND));
		ScheduleMapper.INSTANCE.updateEntityFromRequest(request, schedule);
		schedule.setParticipants(findParticipantsFromRequest(request));
		return ScheduleMapper.INSTANCE.toDto(scheduleRepo.save(schedule));
	}

	private void validateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		LocalDateTime dateNow = LocalDateTime.now();

		/** Start Validations **/
		if (endDate.isBefore(startDate)) {
			throw new FieldValidationException("End date should not be greater than start date", "endDate");
		}
		if (startDate.isBefore(dateNow) || endDate.isBefore(dateNow)) {
			throw new ApiException("Dates should not be less then today", HttpStatus.BAD_REQUEST);
		}
		/** End Validations **/


	}
}
