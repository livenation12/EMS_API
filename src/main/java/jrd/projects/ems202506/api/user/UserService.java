package jrd.projects.ems202506.api.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.role.Role;
import jrd.projects.ems202506.api.role.RoleRepo;
import jrd.projects.ems202506.api.user.dto.UserDto;
import jrd.projects.ems202506.api.user.dto.UserMinDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;

	private final RoleRepo roleRepo;

	public void assignRoles(Long userId, Set<Long> roleIds) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
		Set<Role> roleList = roleRepo.findAllById(roleIds).stream().collect(Collectors.toSet());

		if(roleIds.size() != roleList.size()) {
			throw new ApiException("Some roles not found", HttpStatus.BAD_REQUEST);
		}

		user.setRoles(roleList);
		userRepo.save(user);
	}

	public List<UserMinDto> readAll(){
		List<User> userList = userRepo.findAll();
		return UserMapper.INSTANCE.toUserDtoList(userList);
	}

	public UserDto readById(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
		return UserMapper.INSTANCE.toDto(user);
	}
}
