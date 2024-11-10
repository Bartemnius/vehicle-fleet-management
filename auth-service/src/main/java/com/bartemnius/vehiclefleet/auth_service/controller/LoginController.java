package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.LoginRequest;
import com.bartemnius.vehiclefleet.auth_service.dto.RegisterRequest;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import com.bartemnius.vehiclefleet.auth_service.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

    return ResponseEntity.ok("Login successful!");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    loginService.registerUser(registerRequest);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("User created successfully!");
  }
}
