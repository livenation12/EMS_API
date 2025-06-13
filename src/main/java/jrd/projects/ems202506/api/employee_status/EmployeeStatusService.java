package jrd.projects.ems202506.api.employee_status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jrd.projects.ems202506.api.employee.Employee;
import jrd.projects.ems202506.api.employee.EmployeeRepo;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusDto;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;

@Service
public class EmployeeStatusService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeStatusRepo statusRepo;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Transactional
	public void updateStatus(EmployeeStatusRequestDto request) {
		List<Employee> employees = employeeRepo.findAllById(request.getEmployeeIds());
		List<EmployeeStatus> newStatuses = new ArrayList<>();
		List<EmployeeStatusDto> payloads = new ArrayList<>();
		for(Employee employee : employees) {
			EmployeeStatus newStatus = new EmployeeStatus();
			newStatus.setStatus(request.getStatus());
			newStatus.setTask(request.getTask());
			newStatus.setEmployee(employee);
			newStatus.setTimestamp(LocalDateTime.now());
			newStatuses.add(newStatus);

			//Employee update
			employee.setCurrentStatus(request.getStatus());

			//WebSocket payload
			EmployeeStatusDto statusDto = new EmployeeStatusDto();
			statusDto.setEmployeeId(employee.getId());
			statusDto.setStatus(newStatus.getStatus());
			statusDto.setTask(newStatus.getTask());
			payloads.add(statusDto);

		}
		employeeRepo.saveAll(employees);
		statusRepo.saveAll(newStatuses);
		messagingTemplate.convertAndSend("/topic/status", payloads);
	}
}
