package com.bartemnius.vehiclefleet.authservice.dto;

import com.bartemnius.vehiclefleet.authservice.utils.Role;
import com.bartemnius.vehiclefleet.authservice.utils.TwoFactorMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "All information that you can get about user")
public class UserDto {
  private UUID id;
  private String username;
  private String email;
  private String phoneNumber;

  @Schema(example = "USER")
  private Role role;

  @Schema(example = "true")
  private boolean isTwoFactorEnabled;

  @Schema(example = "EMAIL")
  private TwoFactorMethod twoFactorMethod;
}
