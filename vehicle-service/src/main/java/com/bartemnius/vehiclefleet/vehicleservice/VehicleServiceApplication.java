package com.bartemnius.vehiclefleet.vehicleservice;

import com.bartemnius.vehiclefleet.vehicleservice.config.AuthServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AuthServiceProperties.class)
public class VehicleServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VehicleServiceApplication.class, args);
  }
}
