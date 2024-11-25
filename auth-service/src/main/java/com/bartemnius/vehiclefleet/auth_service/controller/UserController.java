package com.bartemnius.vehiclefleet.auth_service.controller;

import com.bartemnius.vehiclefleet.auth_service.dto.UserDto;
import com.bartemnius.vehiclefleet.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted or alredy gone :)");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest) {
//
//    }
}
