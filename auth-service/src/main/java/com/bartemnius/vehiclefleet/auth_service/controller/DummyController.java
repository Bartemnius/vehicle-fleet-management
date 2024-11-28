package com.bartemnius.vehiclefleet.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO delete later on

@RestController
public class DummyController {

  @GetMapping("/user/hello")
  public String userCanAccess() {
    return "hello user!";
  }

  @GetMapping("/admin/hello")
  public String adminCanAccess() {
    return "Welcome dear admin! Hats off!";
  }
}
