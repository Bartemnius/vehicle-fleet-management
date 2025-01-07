package com.bartemnius.vehiclefleet.authservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO : more tests maybe in future
// TODO : 2FA

@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Authorization service REST API documentation",
            description = "This microservice is used for user authentication and authorization",
            contact = @Contact(name = "Bartemnius", email = "wojtekdluski@wp.pl")))
public class AuthServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServiceApplication.class, args);
  }
}
