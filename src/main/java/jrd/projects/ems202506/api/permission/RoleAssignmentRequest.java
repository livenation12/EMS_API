package jrd.projects.ems202506.api.permission;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RoleAssignmentRequest {

	@NotEmpty(message = "Roles is required")
	private Set<Long> roleIdList;
}
