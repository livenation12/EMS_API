package jrd.projects.ems202506.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jrd.projects.ems202506.api.auth.dto.AuthDto;
import jrd.projects.ems202506.api.auth.dto.LoginRequest;
import jrd.projects.ems202506.api.auth.dto.RegisterRequest;
import jrd.projects.ems202506.api.exception.ApiException;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public AuthDto login(LoginRequest request) {
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
			return AuthMapper.INSTANCE.toDto(principal);
		} catch (Exception e) {
			throw new RuntimeException("Invalid email or password");
		}
	}

	public AuthDto register(RegisterRequest request) {
		try {
			if(userRepo.existsByEmail(request.getEmail())) {
				throw new IllegalArgumentException("Email is already in use");
			}
			User user = new User();
			user.setEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			userRepo.save(user);
			return AuthMapper.INSTANCE.toDto(user);
		} catch (Exception e) {
			throw new ApiException("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public AuthDto verifyToken() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			SecurityContextHolder.clearContext();
			throw new ApiException("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		System.out.println(principal.getRoles());
		return AuthMapper.INSTANCE.toDto(principal);
	}

}
