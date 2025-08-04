package jrd.projects.ems202506.api.permission;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.permission.dto.PermissionDto;
import jrd.projects.ems202506.api.permission.dto.PermissionRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/permissions")
@RequiredArgsConstructor
public class PermissionController {


	private final PermissionService permissionService;

	@PostMapping
	public ApiResponse<?> createPermission(@RequestBody @Valid PermissionRequest request){
		permissionService.create(request);
		return ApiResponse.success("Permission created successfully");
	}

	@GetMapping
	public ApiResponse<List<PermissionDto>> readAllPermissions(){
		List<PermissionDto> permissionList = permissionService.readAll();
		return ApiResponse.success(permissionList);
	}

}
