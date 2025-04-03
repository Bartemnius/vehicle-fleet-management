package com.bartemnius.vehiclefleet.vehicleservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

  private final AuthServiceProperties properties;

  @Bean
  public WebClient webClient() {
    return WebClient.builder().baseUrl(properties.getBaseUrl()).build();
  }
}
