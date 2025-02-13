package com.bartemnius.vehiclefleet.authservice.controller;

import com.bartemnius.vehiclefleet.authservice.utils.JWTUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JWTUtil jwtUtil;

  @GetMapping("/validate")
  public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
    try {
      String username = jwtUtil.extractUsername(token);
      if (!jwtUtil.validateToken(token, username)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false));
      }

      String role = jwtUtil.extractRole(token);

      return ResponseEntity.ok(
          Map.of(
              "valid", true,
              "username", username,
              "role", role));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("valid", false, "error", e.getMessage()));
    }
  }
}
