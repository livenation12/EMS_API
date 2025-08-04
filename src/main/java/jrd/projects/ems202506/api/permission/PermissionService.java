package jrd.projects.ems202506.api.permission;

import java.util.List;

import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.permission.dto.PermissionDto;
import jrd.projects.ems202506.api.permission.dto.PermissionRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

	private final PermissionRepo permissionRepo;

	public void create(PermissionRequest request) {
		Permission permission = new Permission();
		permission.setName(request.getName());
		permission.setDescription(request.getDescription());
		permissionRepo.save(permission);
	}

	public List<PermissionDto> readAll(){
		List<Permission> permissionList = permissionRepo.findAll();
		return PermissionMapper.INSTANCE.toDtoList(permissionList);
	}

}
