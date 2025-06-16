package jrd.projects.ems202506.api.employee_status_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.employee_status_type.dto.EmployeeStatusTypeDto;
import jrd.projects.ems202506.api.employee_status_type.dto.EmployeeStatusTypeRequestDto;

@Service
public class EmployeeStatusTypeService {


	@Autowired
	private EmployeeStatusTypeRepo statusTypeRepo;

	public EmployeeStatusTypeDto createType(EmployeeStatusTypeRequestDto request) {
		EmployeeStatusType statusType = new EmployeeStatusType();
		statusType.setLabel(request.getLabel());
		System.out.println(request.getColorCode());
		statusType.setColorCode(request.getColorCode());
		statusTypeRepo.save(statusType);
		return new EmployeeStatusTypeDto(statusType);
	}

	public List<EmployeeStatusTypeDto> readAll() {
		List<EmployeeStatusType> statusList = statusTypeRepo.findAll();
		return  statusList.stream().map(EmployeeStatusTypeDto::new).toList();
	}
}
