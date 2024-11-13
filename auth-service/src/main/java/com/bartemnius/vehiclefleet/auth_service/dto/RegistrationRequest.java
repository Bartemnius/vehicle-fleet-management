package com.bartemnius.vehiclefleet.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationRequest {
  @NotBlank(message = "Username can not be blank")
  private String username;

  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$",
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character, and be at least 12 characters long")
  private String password;

  @Email(message = "Invalid email format. Email format must be preserved. Ex. text@text.com")
  private String email;

  @Pattern(
      regexp = "^\\+?[0-9]{9,15}$",
      message = "Invalid phone number format. Phone number must have 9 t 15 digits!")
  private String phoneNumber;
}
