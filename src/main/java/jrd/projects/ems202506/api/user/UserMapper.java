package jrd.projects.ems202506.api.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.employee.EmployeeMapper;
import jrd.projects.ems202506.api.user.dto.UserDto;
import jrd.projects.ems202506.api.user.dto.UserMinDto;

@Mapper(uses = { EmployeeMapper.class })
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto toDto(User entity);

	List<UserMinDto> toUserDtoList(List<User> user);

}
