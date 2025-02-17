package com.bartemnius.vehiclefleet.vehicleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class VehicleServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VehicleServiceApplication.class, args);
  }
}
