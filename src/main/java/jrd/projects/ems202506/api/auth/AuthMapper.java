package jrd.projects.ems202506.api.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.auth.dto.AuthDto;
import jrd.projects.ems202506.api.auth.dto.RegisterRequest;
import jrd.projects.ems202506.api.auth.dto.UserEmployeeDto;
import jrd.projects.ems202506.api.employee.EmployeeMapper;

@Mapper(uses = { EmployeeMapper.class })
public interface AuthMapper {

	AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

	AuthDto toDto(User user);

	@Mapping(source = "username", target = "email")
	@Mapping(source = "roles", target = "roles")
	AuthDto toDto(UserPrincipal principal);

	User toEntity(RegisterRequest request);

	@Mapping(source = "employee", target = "employee")
	UserEmployeeDto toUserEmployeeDto(User user);
}
