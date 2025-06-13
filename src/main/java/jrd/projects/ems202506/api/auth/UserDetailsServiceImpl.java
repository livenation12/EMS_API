package jrd.projects.ems202506.api.auth;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.exception.ApiException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user =  userRepo.findByEmail(email)
				.orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
		Set<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toSet());

		return new UserPrincipal(user, authorities);
	}
}