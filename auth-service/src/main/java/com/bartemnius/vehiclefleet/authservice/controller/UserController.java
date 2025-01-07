package com.bartemnius.vehiclefleet.authservice.controller;

import com.bartemnius.vehiclefleet.authservice.dto.UpdateUserRequest;
import com.bartemnius.vehiclefleet.authservice.dto.UserDto;
import com.bartemnius.vehiclefleet.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "User Controller",
    description =
        "CRUD operation excluding create are here. Create happens through RegistrationController")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @Operation(
      summary = "Get user info",
      description = "Get all user information by providing user id")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Get user info"),
    @ApiResponse(responseCode = "404", description = "User with given id does not exists")
  })
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
    UserDto user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @Operation(summary = "Delete user", description = "Delete user with given id.")
  @ApiResponses({@ApiResponse(responseCode = "204", description = "User deleted")})
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted or alredy gone :)");
  }

  @Operation(
      summary = "Update user",
      description = "PUT request for updating user info like username, email or password")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "User info is updated "),
    @ApiResponse(responseCode = "404", description = "User with given id does not exists"),
    @ApiResponse(
        responseCode = "409",
        description =
            "User with given username/email/phoneNumber already exists and that's why can not be updated.")
  })
  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(
      @PathVariable UUID id, @RequestBody UpdateUserRequest updateRequest) {
    userService.updateUser(id, updateRequest);
    return ResponseEntity.ok("User updated successfully");
  }
}
