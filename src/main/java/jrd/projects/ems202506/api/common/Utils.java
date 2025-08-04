package jrd.projects.ems202506.api.common;


import org.springframework.security.core.context.SecurityContextHolder;

import jrd.projects.ems202506.api.auth.UserPrincipal;
import jrd.projects.ems202506.api.user.User;

public class Utils {

	public static User getAuthUser() {
		UserPrincipal principal = getPrincipal();
		return principal.getUser();
	}

	public static UserPrincipal getPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}


