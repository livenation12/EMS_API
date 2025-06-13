package jrd.projects.ems202506.api.employee_status;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.employee_status.dto.EmployeeStatusRequestDto;

@Mapper
public interface EmployeeStatusMapper {

	EmployeeStatusMapper INSTANCE = Mappers.getMapper(EmployeeStatusMapper.class);


	EmployeeStatus toEntity(EmployeeStatusRequestDto request);

}
