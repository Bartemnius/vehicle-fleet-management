package com.bartemnius.vehiclefleet.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login request with login and password to validate user")
public record LoginRequest(
    @Schema(description = "Unique username chosen during registration", example = "CustomUserName")
        String username,
    @Schema(description = "User password", example = "Your$Passw0rd;)") String password) {}
