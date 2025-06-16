package jrd.projects.ems202506.api.employee_status_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.employee_status_type.dto.EmployeeStatusTypeDto;
import jrd.projects.ems202506.api.employee_status_type.dto.EmployeeStatusTypeRequestDto;

@RestController
@RequestMapping("/api/employees/status/types")
public class EmployeeStatusTypeController {

	@Autowired
	private EmployeeStatusTypeService statusTypeService;

	@PostMapping
	public ApiResponse<EmployeeStatusTypeDto> createEmployeeStatusType(@RequestBody @Valid EmployeeStatusTypeRequestDto request){
		EmployeeStatusTypeDto newStatusType = statusTypeService.createType(request);
		return ApiResponse.success(newStatusType, "Status type successfully created");
	}

	@GetMapping
	public ApiResponse<List<EmployeeStatusTypeDto>> readAllStatusType(){
		List<EmployeeStatusTypeDto> statusList = statusTypeService.readAll();
		return ApiResponse.success(statusList);
	}
}
