package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.api.UserRequest;
import com.web.shop.model.table.User;
import com.web.shop.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

  @Autowired private UserService userService;
  private static final Logger logger = LogManager.getLogger();

  @PostMapping
  public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid UserRequest user)
      throws Exception {
    User savedUser = userService.createUser(user);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.CREATED.value())
            .message("User created successfully")
            .data(savedUser)
            .build());
  }

  @GetMapping
  public ResponseEntity<ApiResponse> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(
        ApiResponse.builder().code(HttpStatus.OK.value()).message("Success").data(users).build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
    return userService
        .getUserById(id)
        .map(
            user ->
                ResponseEntity.ok(
                    ApiResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .data(user)
                        .build()))
        .orElse(
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    ApiResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("User not found")
                        .build()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody User user) {
    User updated = userService.updateUser(id, user);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("User updated successfully")
            .data(updated)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("User deleted successfully")
            .build());
  }
}
