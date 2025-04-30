package com.web.shop.model.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
  @Email(message = "Enter a valid email")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  private String password;
}
