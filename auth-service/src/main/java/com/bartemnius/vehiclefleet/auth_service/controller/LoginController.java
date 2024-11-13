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
//  checking password
//  checking email and username
//  checking password complication level 12 signs 1 small 1 big etc.
//  creating mappings for dtos
//  create documentation for all endpoints - openapi
//  delete user
//  update user

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
