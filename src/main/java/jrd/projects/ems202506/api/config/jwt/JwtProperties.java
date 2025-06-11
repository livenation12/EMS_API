package jrd.projects.ems202506.api.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class JwtProperties {
	@Value("${jwt.cookie.auth_name}")
	private String authCookieName;

	@Value("${jwt.cookie.secretkey}")
	private String secretKey;

}
