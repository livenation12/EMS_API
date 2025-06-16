package jrd.projects.ems202506.api.employee_status_type;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.employee_status_type.dto.EmployeeStatusTypeDto;

@Mapper
public interface EmployeeStatusTypeMapper {


	EmployeeStatusTypeMapper INSTANCE = Mappers.getMapper(EmployeeStatusTypeMapper.class);


	EmployeeStatusTypeDto toDto(EmployeeStatusType entity);

}
