package jrd.projects.ems202506.api.permission;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.permission.dto.PermissionDto;
import jrd.projects.ems202506.api.permission.dto.PermissionRequest;

@Mapper
public interface PermissionMapper extends BaseMapper<PermissionRequest, PermissionDto, Permission> {
	PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

	Set<PermissionDto> toDtoSet(Set<Permission> permissions);
}
