package jrd.projects.ems202506.api.schedule_type;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeDto;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedules/types")
@RequiredArgsConstructor
public class ScheduleTypeController {

	private final ScheduleTypeService schedTypeService;

	@PostMapping
	public ApiResponse<?> create(@RequestBody @Valid ScheduleTypeRequest request){
		schedTypeService.create(request);
		return ApiResponse.success("Schedule type successfully created");
	}

	@GetMapping
	public ApiResponse<List<ScheduleTypeDto>> readAll(){
		List<ScheduleTypeDto> schedTypeList = schedTypeService.readAll();
		return ApiResponse.success(schedTypeList);
	}
}
