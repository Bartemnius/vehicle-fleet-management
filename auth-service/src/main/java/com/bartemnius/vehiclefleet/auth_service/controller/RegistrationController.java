package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.RegistrationRequest;
import com.bartemnius.vehiclefleet.auth_service.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO 2FA
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<String> register(
      @Valid @RequestBody RegistrationRequest registrationRequest) {
    registrationService.registerUser(registrationRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
  }
}
