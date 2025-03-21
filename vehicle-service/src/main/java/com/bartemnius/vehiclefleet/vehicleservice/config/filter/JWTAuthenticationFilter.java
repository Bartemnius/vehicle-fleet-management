package com.bartemnius.vehiclefleet.vehicleservice.config.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This filter intercepts all incoming HTTP requests to check if they contain a valid JWT token. -
 * If the request has an "Authorization: Bearer <token>" header, the token is validated. - The
 * filter calls the Auth Service to verify the token. - If the token is valid, the user details
 * (username and role) are extracted. - The user is authenticated and stored in the SecurityContext.
 * - If the token is invalid, the request is rejected with HTTP 401 (Unauthorized).
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);
    String url = "http://localhost:8080/auth/validate?token=" + token;

    try {
      String jsonResponse = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();

      JsonNode jsonNode = objectMapper.readTree(jsonResponse);

      if (!jsonNode.get("valid").asBoolean()) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid token");
        return;
      }

      String username = jsonNode.get("username").asText();
      String role = jsonNode.get("role").asText();
      List<GrantedAuthority> authorities = Collections.singletonList(() -> "ROLE_" + role);

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(username, token, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid token");
      return;
    }

    chain.doFilter(request, response);
  }
}
