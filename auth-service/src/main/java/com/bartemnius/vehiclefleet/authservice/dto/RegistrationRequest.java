package com.bartemnius.vehiclefleet.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Object used to register new user")
public class RegistrationRequest {
  @NotBlank(message = "Username can not be blank")
  @Schema(description = "Unique username", example = "UniqueUserName")
  private String username;

  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$",
      message =
          "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character, and be at least 12 characters long")
  @Schema(
      description =
          "Password chosen by user. Min 12 character long, 1 uppercase letter and 1 lowercase, special character is needed as well",
      example = "YouPa$$word1!")
  private String password;

  @Email(message = "Invalid email format. Email format must be preserved. Ex. text@text.com")
  @Schema(
      description = "Unique email. Email already used can not be assigned.",
      example = "email@gmail.com")
  private String email;

  @Pattern(
      regexp = "^\\+?[0-9]{9,15}$",
      message = "Invalid phone number format. Phone number must have 9 t 15 digits!")
  @Schema(
      description = "Phone number min 9 char long max 15 char long. Only digits and + sign",
      example = "+48123123123 or 123123123")
  private String phoneNumber;
}
