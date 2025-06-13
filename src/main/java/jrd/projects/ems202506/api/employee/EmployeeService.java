package jrd.projects.ems202506.api.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.common.SearchPagingRequest;
import jrd.projects.ems202506.api.common.SearchSpecification;
import jrd.projects.ems202506.api.employee.dto.EmployeeDto;
import jrd.projects.ems202506.api.employee.dto.EmployeeRequestDto;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	EmployeeDto createEmployee(EmployeeRequestDto request) {
		Employee employee = employeeRepo.save(EmployeeMapper.INSTANCE.toEntity(request));
		return EmployeeMapper.INSTANCE.toDto(employee);
	}

	public Page<EmployeeDto> readAllEmployees(SearchPagingRequest request) {
		//make a pageable
		Pageable pageable = PageRequest.of(
				request.getPageNumber(),
				request.getSize(),
				Sort.by(request.getSortBy()).descending()
				);
		Page<Employee> page = employeeRepo.findAll(
				SearchSpecification.searchByField(request.getSearchBy(), request.getKeyword()),
				pageable
				);
		return page.map(EmployeeMapper.INSTANCE::toDto); // preserve pagination
	}


}
