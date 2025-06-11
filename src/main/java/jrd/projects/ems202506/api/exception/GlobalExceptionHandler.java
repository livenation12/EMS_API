package jrd.projects.ems202506.api.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jrd.projects.ems202506.api.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	ResponseEntity<ApiResponse<String>> handleApiException(ApiException ex) {
		return ResponseEntity
				.status(ex.getStatus())
				.body(ApiResponse.error(ex.getMessage()));
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	ApiResponse<String> handleServerException(Exception ex){
		return ApiResponse.error("Server error");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	ApiResponse<String> illegalArgsErrors(IllegalArgumentException ex){
		return ApiResponse.error(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	ApiResponse<String> runtimeErrors(RuntimeException ex){
		return ApiResponse.error(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ApiResponse<Map<String, Object>> validationErrors(MethodArgumentNotValidException ex){
		Map<String, Object> response = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
		return ApiResponse.error(response);
	}
}
