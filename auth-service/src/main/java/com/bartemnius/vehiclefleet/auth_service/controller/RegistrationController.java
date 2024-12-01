package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.RegistrationRequest;
import com.bartemnius.vehiclefleet.auth_service.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Registration Controller", description = "Users register through this endpoint.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @Operation(summary = "Register new user into application")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "User successfully created"),
    @ApiResponse(
        responseCode = "409",
        description =
            "User has some fields that are already used by another user, like username, email or password. The message return will defined which of those is already in use. ")
  })
  @PostMapping("/register")
  public ResponseEntity<String> register(
      @Valid @RequestBody RegistrationRequest registrationRequest) {
    registrationService.registerUser(registrationRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
  }
}
