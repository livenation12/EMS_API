package jrd.projects.ems202506.api.auth;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.exception.ApiException;
import jrd.projects.ems202506.api.user.User;
import jrd.projects.ems202506.api.user.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.flatMap(role -> {
					Stream<GrantedAuthority> roleStream = Stream.of(
							new SimpleGrantedAuthority("ROLE_" + role.getName())
							);
					Stream<GrantedAuthority> permissionStream = role.getPermissions().stream()
							.map(permission -> new SimpleGrantedAuthority(permission.getName()));

					return Stream.concat(roleStream, permissionStream);

				}).collect(Collectors.toSet());
		return new UserPrincipal(user, authorities);
	}

}