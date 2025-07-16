package jrd.projects.ems202506.api.employee_status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusDto;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;

@RestController
@RequestMapping("/api/employees/status")
public class EmployeeStatusController {

	@Autowired
	private EmployeeStatusService employeeStatusService;


	@PatchMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<?> employeeStatusUpdate(@RequestBody @Valid EmployeeStatusRequestDto request){
		employeeStatusService.updateStatus(request);
		return ApiResponse.success("Employee status updated");
	}

	@GetMapping("/{employeeId}/latest")
	public ApiResponse<List<EmployeeStatusDto>> getLatestStatusByEmployee(@PathVariable Long employeeId){
		List<EmployeeStatusDto> employeeStatusList = employeeStatusService.readEmployeeStatusByEmployeeId(employeeId);
		return ApiResponse.success(employeeStatusList);
	}

	@GetMapping("/latest")
	public ApiResponse<List<EmployeeStatusDto>> getLatestStatuses(){
		List<EmployeeStatusDto> latestStatus = employeeStatusService.readLatestStatus();
		return ApiResponse.success(latestStatus);
	}

}
