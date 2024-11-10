package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.LoginRequest;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class LoginController {

  private final User testUser = new User("test", "password");

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    if (testUser.getUsername().equals(loginRequest.getUsername())
        && testUser.getPassword().equals(loginRequest.getPassword())) {
      return ResponseEntity.ok("Login ok!");
    } else {
      return ResponseEntity.status(401).body("Incorrect!");
    }
  }
}
