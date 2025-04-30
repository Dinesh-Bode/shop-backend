package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.api.LoginRequest;
import com.web.shop.model.table.User;
import com.web.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
    try {
      User user = userService.login(request.getEmail(), request.getPassword());

      ApiResponse apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.OK.value())
              .message("Login successful")
              .data(user)
              .build();
      return ResponseEntity.ok(apiResponse);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(
              ApiResponse.builder()
                  .code(HttpStatus.UNAUTHORIZED.value())
                  .message("Login failed")
                  .data(null)
                  .build());
    }
  }
}
