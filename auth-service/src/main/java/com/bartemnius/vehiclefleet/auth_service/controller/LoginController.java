package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.LoginRequest;
import com.bartemnius.vehiclefleet.auth_service.service.LoginService;
import com.bartemnius.vehiclefleet.auth_service.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: add some advanced logic:
//  create documentation for all endpoints - openapi

// TODO 2FA
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;
  private final JWTUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    loginService.validateLogin(loginRequest);
    String token = jwtUtil.generateToken(loginRequest.username());
    return ResponseEntity.status(HttpStatus.OK).body("Bearer:" + token);
  }
}
