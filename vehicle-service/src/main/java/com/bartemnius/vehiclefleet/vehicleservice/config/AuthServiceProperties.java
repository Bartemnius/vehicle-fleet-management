package com.bartemnius.vehiclefleet.vehicleservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth-service")
@Data
public class AuthServiceProperties {
    private String baseUrl;
    private String host;
    private int port;
}
