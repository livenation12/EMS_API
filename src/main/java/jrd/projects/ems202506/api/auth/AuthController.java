package jrd.projects.ems202506.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jrd.projects.ems202506.api.auth.dto.AuthDto;
import jrd.projects.ems202506.api.auth.dto.LoginRequest;
import jrd.projects.ems202506.api.auth.dto.RegisterRequest;
import jrd.projects.ems202506.api.common.ApiResponse;
import jrd.projects.ems202506.api.config.jwt.JwtService;
import jrd.projects.ems202506.api.exception.ApiException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtService jwtService;


	@PostMapping("/login")
	public ApiResponse<AuthDto> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response){
		AuthDto authUser = authService.login(request);
		String token = jwtService.generateToken(authUser.getEmail());
		Cookie jwtCookie = jwtService.setJwtCookie(token);
		response.addCookie(jwtCookie);
		return ApiResponse.success(authUser, "Logged in successfully");
	}

	@PostMapping("/logout")
	public ApiResponse<?> logout(HttpServletResponse response) {
		SecurityContextHolder.clearContext();
		response.addCookie(jwtService.destroyJwtCookie());
		return ApiResponse.success("Successfully logged out");

	}

	@PostMapping("/register")
	public ApiResponse<AuthDto> register(@RequestBody @Valid RegisterRequest request){
		AuthDto register = authService.register(request);
		return ApiResponse.success(register, "Registration successful");
	}

	@GetMapping("/verify-token")
	public ApiResponse<AuthDto> verifyToken(HttpServletResponse response) {
		try {
			AuthDto dto = authService.verifyToken();
			return ApiResponse.success(dto);
		} catch (ApiException e) {
			Cookie clearedCookie = jwtService.destroyJwtCookie();
			response.addCookie(clearedCookie);
			throw e;
		}
	}


}
