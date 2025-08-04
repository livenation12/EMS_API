package jrd.projects.ems202506.api.role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.permission.Permission;
import jrd.projects.ems202506.api.role.dto.RoleDto;
import jrd.projects.ems202506.api.role.dto.RoleRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepo roleRepo;
	private final EntityManager entityManager;

	public void assignRole() {

	}

	public void create(RoleRequest request) {
		Role role = RoleMapper.INSTANCE.toEntity(request);

		Set<Permission> permissionSet = new HashSet<>();

		if(!request.getPermissionIds().isEmpty()) {
			for (Long id : request.getPermissionIds()) {
				Permission permissionRef = entityManager.getReference(Permission.class, id);
				permissionSet.add(permissionRef);
			}
		}
		role.setPermissions(permissionSet);
		roleRepo.save(role);
	}

	public List<RoleDto> readAll(){
		List<Role> roleList = roleRepo.findAll();
		return RoleMapper.INSTANCE.toDtoList(roleList);
	}

	public RoleDto readById(Long id) {
		Role role = roleRepo.findById(id).orElseThrow(() -> new ApiException("Role not found",  HttpStatus.NOT_FOUND));
		return RoleMapper.INSTANCE.toDto(role);
	}
}
