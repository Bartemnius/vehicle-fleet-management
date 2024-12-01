package com.bartemnius.vehiclefleet.auth_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO delete later on

@Tag(
    name = "Dummy controller",
    description = "For now it's used for test purposes to see if authorization works correctly")
@RestController
public class DummyController {

  @Operation(summary = "", description = "")
  @ApiResponses({@ApiResponse(responseCode = "", description = "")})
  @GetMapping("/user/hello")
  public String userCanAccess() {
    return "hello user!";
  }

  @Operation(summary = "", description = "")
  @ApiResponses({@ApiResponse(responseCode = "", description = "")})
  @GetMapping("/admin/hello")
  public String adminCanAccess() {
    return "Welcome dear admin! Hats off!";
  }
}
