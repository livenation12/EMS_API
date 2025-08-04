package jrd.projects.ems202506.api.auth;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jrd.projects.ems202506.api.permission.Permission;
import jrd.projects.ems202506.api.user.User;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final User user;
	private final Set<GrantedAuthority> authorities;

	public UserPrincipal(User user, Set<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public Set<Permission> getPermissions() {
		return user.getRoles().stream()
				.flatMap(role -> role.getPermissions().stream())
				.collect(Collectors.toSet());
	}

	public Set<String> getRoles() {
		return user.getRoles().stream().map(role -> "ROLE_" + role.getName()).collect(Collectors.toSet());
	}

	public User getUser() {
		return user;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
