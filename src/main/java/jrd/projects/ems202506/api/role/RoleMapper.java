package jrd.projects.ems202506.api.role;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.permission.PermissionMapper;
import jrd.projects.ems202506.api.role.dto.RoleDto;
import jrd.projects.ems202506.api.role.dto.RoleRequest;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper extends BaseMapper<RoleRequest, RoleDto, Role> {
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
