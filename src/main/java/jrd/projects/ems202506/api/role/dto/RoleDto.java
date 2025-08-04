package jrd.projects.ems202506.api.role.dto;

import java.util.Set;

import jrd.projects.ems202506.api.permission.dto.PermissionDto;
import lombok.Data;

@Data
public class RoleDto {
	private Long id;
	private String name;
	private String description;
	private Set<PermissionDto> permissions;
}
