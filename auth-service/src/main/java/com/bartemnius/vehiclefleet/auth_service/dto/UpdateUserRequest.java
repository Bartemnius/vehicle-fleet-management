package com.bartemnius.vehiclefleet.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
  @NotBlank(message = "Username can not be blank")
  private String username;

  @Email(message = "Invalid email format")
  private String email;

  @Pattern(
      regexp = "^\\+?[0-9]{9,15}$",
      message = "Invalid phone number format. Phone number must have 9 t 15 digits!")
  private String phoneNumber;
}
