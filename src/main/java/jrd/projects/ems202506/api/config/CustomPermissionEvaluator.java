package jrd.projects.ems202506.api.config;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	private boolean checkPermission(Authentication authentication, Object permission) {
		if (authentication == null || permission == null) {
			return false;
		}

		// Shortcut: allow all for admins
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
		if (isAdmin) {
			return true;
		}

		// Otherwise, check for specific permission
		String requiredPermission = permission.toString();
		return authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(auth -> auth.equals(requiredPermission));
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return checkPermission(authentication, permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return checkPermission(authentication, permission);
	}
}
