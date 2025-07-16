package jrd.projects.ems202506.api.schedule;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.schedule.dto.DateRangeFilter;
import jrd.projects.ems202506.api.schedule.dto.ScheduleDto;
import jrd.projects.ems202506.api.schedule.dto.ScheduleRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping
	public ApiResponse<ScheduleDto> createSchedule(@RequestBody @Valid ScheduleRequestDto request){
		ScheduleDto newSchedule = scheduleService.create(request);
		return ApiResponse.success(newSchedule);
	}

	@GetMapping("/by-range")
	public ApiResponse<List<ScheduleDto>> readSchedulesByRange(@ModelAttribute DateRangeFilter range){
		List<ScheduleDto> scheduleList = scheduleService.readByRange(range);
		return ApiResponse.success(scheduleList);
	}

	@PutMapping("/{id}")
	public ApiResponse<ScheduleDto> updateById(@RequestBody @Valid ScheduleRequestDto request, @PathVariable Long id){
		ScheduleDto sched = scheduleService.updateById(id, request);
		return ApiResponse.success(sched, "Schedule successfully updated");
	}
}
