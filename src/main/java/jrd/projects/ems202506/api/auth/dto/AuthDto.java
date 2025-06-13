package jrd.projects.ems202506.api.auth.dto;

import java.util.Set;

import lombok.Data;

@Data
public class AuthDto {
	private String email;
	private Set<String> roles;
}
