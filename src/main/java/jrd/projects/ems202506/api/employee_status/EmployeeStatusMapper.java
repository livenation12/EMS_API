package jrd.projects.ems202506.api.employee_status;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusDto;
import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;

@Mapper
public interface EmployeeStatusMapper {

	EmployeeStatusMapper INSTANCE = Mappers.getMapper(EmployeeStatusMapper.class);

	@Mapping(source = "employee.firstName", target = "employeeName")
	@Mapping(source = "employee.id", target = "employeeId")
	EmployeeStatusDto toDto(EmployeeStatus employeeStatus);

	List<EmployeeStatusDto> toDtoList(List<EmployeeStatus> employeeStatusList);

	EmployeeStatus toEntity(EmployeeStatusRequestDto request);
}
