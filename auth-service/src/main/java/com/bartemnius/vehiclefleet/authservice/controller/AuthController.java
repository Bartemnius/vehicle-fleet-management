package com.bartemnius.vehiclefleet.authservice.controller;

import com.bartemnius.vehiclefleet.authservice.entity.User;
import com.bartemnius.vehiclefleet.authservice.repository.UserRepository;
import com.bartemnius.vehiclefleet.authservice.utils.JWTUtil;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final JWTUtil jwtUtil;
  private final UserRepository userRepository;

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

  @GetMapping("/user-id")
  public ResponseEntity<Map<String, Object>> getUserId(@RequestParam String username) {
    log.info("Received request for user-id with username: {}", username);
    Optional<User> user = userRepository.findByUsername(username);

    if (user.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
    }

    return ResponseEntity.ok(Map.of("userId", user.get().getId()));
  }
}
