package jrd.projects.ems202506.api.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

import jrd.projects.ems202506.api.auth.dto.AuthDto;
import jrd.projects.ems202506.api.auth.dto.RegisterRequest;

@Mapper
public interface AuthMapper {

	AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

	AuthDto toDto(User user);

	@Mapping(source = "username", target = "email")
	AuthDto toDto(UserDetails userDetails);

	User toEntity(RegisterRequest request);
}
