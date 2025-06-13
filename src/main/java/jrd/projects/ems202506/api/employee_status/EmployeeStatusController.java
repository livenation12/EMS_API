package jrd.projects.ems202506.api.employee_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;

@RestController
@RequestMapping("/api/employees/status")
public class EmployeeStatusController {

	@Autowired
	private EmployeeStatusService employeeStatusService;


	@PatchMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse<?> employeeStatusUpdate(@RequestBody @Valid EmployeeStatusRequestDto request){
		System.out.println("im admin");
		employeeStatusService.updateStatus(request);
		return ApiResponse.success("Employee status updated");
	}


}
