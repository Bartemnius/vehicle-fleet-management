package com.bartemnius.vehiclefleet.authservice.controller;

import com.bartemnius.vehiclefleet.authservice.dto.LoginRequest;
import com.bartemnius.vehiclefleet.authservice.dto.Response;
import com.bartemnius.vehiclefleet.authservice.entity.User;
import com.bartemnius.vehiclefleet.authservice.service.LoginService;
import com.bartemnius.vehiclefleet.authservice.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Login controller",
    description = "Users login through this endpoint. In feature will be also responsible for 2FA")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;
  private final JWTUtil jwtUtil;

  @Operation(summary = "Login user into application")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "If username and password are correct will login user and return JWT token"),
    @ApiResponse(
        responseCode = "404",
        description = "If username does not exist will return not found"),
    @ApiResponse(
        responseCode = "401",
        description = "If username or password are not correct will return unauthorized ")
  })
  @PostMapping("/login")
  public ResponseEntity<Response<Map<String, String>>> login(
      @RequestBody LoginRequest loginRequest) {
    User userDetails = loginService.validateLogin(loginRequest);
    String token = jwtUtil.generateToken(userDetails);

    Map<String, String> data = new HashMap<>();
    data.put("token", "Bearer:" + token);
    Response<Map<String, String>> response = new Response<>("Login successful!", data);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
