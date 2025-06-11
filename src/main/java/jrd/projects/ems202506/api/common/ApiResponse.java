package jrd.projects.ems202506.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ApiResponse<T> {
	//static errors
	public static <T> ApiResponse<T> error(String message){
		return new ApiResponse<T>(null, message, false);
	}
	public static <T> ApiResponse<T> error(T data){
		return new ApiResponse<T>(data, "error", false);
	}

	//static success
	public static <T> ApiResponse<T> success(T data){
		return new ApiResponse<T>(data, "success", true);
	}
	public static <T> ApiResponse<T> success(T data, String message){
		return new ApiResponse<T>(data, message, true);
	}
	private Boolean success;
	private String message;
	private T data;

	public ApiResponse(T data, String message, Boolean success) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
}
