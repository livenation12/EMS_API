package jrd.projects.ems202506.api.schedule_type;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.schedule_type.dto.ScheduleTypeRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedules/types")
@RequiredArgsConstructor
public class ScheduleTypeController {

	private ScheduleTypeService schedTypeService;

	@PostMapping
	public ApiResponse<?> create(@RequestBody @Valid ScheduleTypeRequest request){
		schedTypeService.create(request);
		return ApiResponse.success("Schedule type successfully created");
	}
}
