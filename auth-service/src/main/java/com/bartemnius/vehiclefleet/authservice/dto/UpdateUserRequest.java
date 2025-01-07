package com.bartemnius.vehiclefleet.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
    description =
        "Info that can be updated in user. To see specific fields see RegistrationRequest")
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
