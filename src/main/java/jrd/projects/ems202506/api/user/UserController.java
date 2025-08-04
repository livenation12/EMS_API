package jrd.projects.ems202506.api.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.permission.RoleAssignmentRequest;
import jrd.projects.ems202506.api.user.dto.UserDto;
import jrd.projects.ems202506.api.user.dto.UserMinDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/{id}/roles")
	public ApiResponse<?> assignRolesToUser(@PathVariable Long id, @RequestBody RoleAssignmentRequest request){
		userService.assignRoles(id, request.getRoleIdList());
		return ApiResponse.success("Roles successfully assigned to user");
	}

	@GetMapping
	public ApiResponse<List<UserMinDto>> readAllUsers(){
		List<UserMinDto> userList = userService.readAll();
		return ApiResponse.success(userList);
	}

	@GetMapping("/{id}")
	public ApiResponse<UserDto> readUserById(@PathVariable Long id){
		UserDto user = userService.readById(id);
		return ApiResponse.success(user);
	}
}
