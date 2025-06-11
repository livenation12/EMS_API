package jrd.projects.ems202506.api.auth.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	private final String requiredMessage = " is required";

	@NotBlank(message = "Email" + requiredMessage)
	@Email(message = "Email must have valid email pattern")
	private String email;

	@Size(min = 6, message="Password must not less than 6")
	@NotBlank(message = "Password" + requiredMessage)
	private String password;

	@Size(min = 6, message="Confirm password must not less than 6")
	@NotBlank(message = "Confirm password" + requiredMessage)
	private String confirmPassword;

	@AssertTrue(message = "Passwords do not match")
	public boolean isPasswordsMatch() {
		return password != null && password.equals(confirmPassword);

	}

}
