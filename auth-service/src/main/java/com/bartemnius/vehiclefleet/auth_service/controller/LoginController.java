package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.LoginRequest;
import com.bartemnius.vehiclefleet.auth_service.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: add some advanced logic:
//  creating mappings for dtos
//  create documentation for all endpoints - openapi
//  jwt token logic


//TODO 2FA
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    loginService.validateLogin(loginRequest);
    return ResponseEntity.status(HttpStatus.OK).body("Login successful!");
  }
}
