package com.bartemnius.vehiclefleet.auth_service.dto;

import com.bartemnius.vehiclefleet.auth_service.utils.Role;
import com.bartemnius.vehiclefleet.auth_service.utils.TwoFactorMethod;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
  private UUID id;
  private String username;
  private String email;
  private String phoneNumber;
  private Role role;
  private boolean isTwoFactorEnabled;
  private TwoFactorMethod twoFactorMethod;
}
