package jrd.projects.ems202506.api.employee_status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee.EmployeeRepo;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusDto;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;
import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusType;
import jrd.projects.ems202506.api.employee_status_type.EmployeeStatusTypeRepo;
import jrd.projects.ems202506.api.exception.ApiException;

@Service
public class EmployeeStatusService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeStatusRepo statusRepo;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private EmployeeStatusTypeRepo statusTypeRepo;

	public List<EmployeeStatusDto> readLatestStatus(){
		List<EmployeeStatus> latestStatus = statusRepo.findTopWithEmployee(PageRequest.of(0, 20));
		return latestStatus.stream().map(EmployeeStatusDto::new).collect(Collectors.toList());
	}

	@Transactional
	public void updateStatus(EmployeeStatusRequestDto request) {
		List<Employee> employees = employeeRepo.findAllById(request.getEmployeeIds());
		List<EmployeeStatus> newStatuses = new ArrayList<>();
		List<EmployeeStatusDto> payloads = new ArrayList<>();
		EmployeeStatusType statusType = statusTypeRepo.findById(request.getStatusId()).orElseThrow(() -> new ApiException("Status type not found", HttpStatus.BAD_REQUEST));
		for(Employee employee : employees) {
			EmployeeStatus newStatus = new EmployeeStatus();
			newStatus.setStatus(statusType);
			newStatus.setTask(request.getTask());
			newStatus.setEmployee(employee);
			newStatus.setTimestamp(LocalDateTime.now());
			newStatuses.add(newStatus);

			//Employee update
			employee.setStatus(statusType);

			//WebSocket payload
			EmployeeStatusDto statusDto = new EmployeeStatusDto();
			statusDto.setId(newStatus.getId());
			statusDto.setEmployeeName(employee.getFullName());
			statusDto.setEmployeeId(employee.getId());
			statusDto.setStatus(statusType);
			statusDto.setTask(newStatus.getTask());
			statusDto.setTimestamp(newStatus.getTimestamp());			payloads.add(statusDto);

		}
		employeeRepo.saveAll(employees);
		statusRepo.saveAll(newStatuses);
		messagingTemplate.convertAndSend("/topic/status", payloads);
	}
}
