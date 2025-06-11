package jrd.projects.ems202506.api.employee;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.employee.dto.EmployeeRequestDto;

@Mapper
public interface EmployeeMapper {
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	EmployeeDto toDto(Employee employee);
	Employee toEntity(EmployeeRequestDto request);
}
