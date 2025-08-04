package jrd.projects.ems202506.api.role.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {

	@NotBlank(message="Role name is required")
	private String name;

	private Set<Long> permissionIds = new HashSet<>();

	private String description;
}
