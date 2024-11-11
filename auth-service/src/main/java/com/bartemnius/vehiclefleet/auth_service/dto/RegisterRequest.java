package com.bartemnius.vehiclefleet.auth_service.dto;

import lombok.Data;

@Data
public class RegisterRequest {
  // TODO two passwords! must match!
  //  password validation
  //
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
}
