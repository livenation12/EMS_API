package jrd.projects.ems202506.api.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.common.SearchPagingRequest;
import jrd.projects.ems202506.api.employee.dto.EmployeeDto;
import jrd.projects.ems202506.api.employee.dto.EmployeeRequestDto;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	ApiResponse<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeRequestDto request){
		return ApiResponse.success(employeeService.createEmployee(request), "Employee created successfully");
	}

	@GetMapping
	ApiResponse<Page<EmployeeDto>> readEmployees(@ModelAttribute SearchPagingRequest request){
		return ApiResponse.success(employeeService.readAllEmployees(request));
	}

























	//	@GetMapping("/test")
	//	ApiResponse<String> test() {
	//		return ApiResponse.success("testing completed");
	//	}
	//
	//	@GetMapping
	//	ApiResponse<EmployeeDto> test2(){
	//		Employee emp = new Employee();
	//		emp.setFirstName("trial");
	//		return ApiResponse.success(EmployeeMapper.INSTANCE.toDto(emp));
	//	}

}
