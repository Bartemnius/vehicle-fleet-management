package com.bartemnius.vehiclefleet.vehicleservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${auth-service.base-url}")
  private String authServiceBaseUrl;

  @Bean
  public WebClient webClient() {
    return WebClient.builder().baseUrl(authServiceBaseUrl).build();
  }
}
