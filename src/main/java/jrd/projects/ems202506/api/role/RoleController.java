package jrd.projects.ems202506.api.role;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.role.dto.RoleDto;
import jrd.projects.ems202506.api.role.dto.RoleRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PostMapping
	public ApiResponse<?> createRole(@RequestBody @Valid RoleRequest request){
		roleService.create(request);
		return ApiResponse.success("Role created successfully");
	}

	@GetMapping
	public ApiResponse<List<RoleDto>> readAllRoles(){
		List<RoleDto> roleList = roleService.readAll();
		return ApiResponse.success(roleList);
	}

	@GetMapping("/{id}")
	public ApiResponse<RoleDto> readRoleById(@PathVariable Long id){
		RoleDto role = roleService.readById(id);
		return ApiResponse.success(role);
	}

}
