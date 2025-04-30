package com.web.shop.model.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
  @NotBlank(message = "First name cannot be blank")
  private String firstName;

  @NotBlank(message = "Last name cannot be blank")
  private String lastName;

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Enter a valid email address")
  private String email;

  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit phone number")
  private String phoneNumber;

  @NotBlank(message = "Password should not be blank")
  @Size(min = 5, message = "Password should have minimum 5 characters")
  private String password;
}
