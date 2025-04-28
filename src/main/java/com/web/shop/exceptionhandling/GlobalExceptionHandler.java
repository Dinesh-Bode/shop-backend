package com.web.shop.exceptionhandling;

import com.web.shop.model.api.ApiResponse;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  // Handle Generic exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleException(Exception e) {
    ApiResponse apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Internal Server Error :: " + e.getMessage())
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Handle specific exceptions
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleUserNotFoundException(Exception e) {
    ApiResponse apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .message("User not found :: " + e.getMessage())
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ApiResponse> userExistsException(Exception e) {
    ApiResponse apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.CONFLICT.value())
            .message("User already exists for given user request :: " + e.getMessage())
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    String errorMessage =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

    ApiResponse response =
        ApiResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message(errorMessage).build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
