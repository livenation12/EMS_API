package jrd.projects.ems202506.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jrd.projects.ems202506.api.auth.dto.AuthDto;
import jrd.projects.ems202506.api.auth.dto.LoginRequest;
import jrd.projects.ems202506.api.auth.dto.RegisterRequest;
import jrd.projects.ems202506.api.common.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ApiResponse<AuthDto> login(@RequestBody @Valid LoginRequest request){
		return ApiResponse.success(authService.login(request));
	}

	@PostMapping("/register")
	public ApiResponse<AuthDto> register(@RequestBody @Valid RegisterRequest request){
		AuthDto register = authService.register(request);
		return ApiResponse.success(register, "Registration successful");
	}

}
