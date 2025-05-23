package com.bartemnius.vehiclefleet.authservice.service;

import com.bartemnius.vehiclefleet.authservice.dto.LoginRequest;
import com.bartemnius.vehiclefleet.authservice.entity.User;
import com.bartemnius.vehiclefleet.authservice.exception.UnexpectedErrorException;
import com.bartemnius.vehiclefleet.authservice.exception.UserDoesNotExistsException;
import com.bartemnius.vehiclefleet.authservice.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public User validateLogin(LoginRequest loginRequest) {
    if (!userService.userExists(loginRequest.username())) {
      log.warn("Login attempt with non-existing username: {}", loginRequest.username());
      throw new UserDoesNotExistsException("User does not exist!");
    }

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.username(), loginRequest.password()));
      log.info("Login successful for username: {}", loginRequest.username());
    } catch (BadCredentialsException ex) {
      log.warn("Invalid password for username: {}", loginRequest.username());
      throw new WrongPasswordException("Provided password is not correct!");
    } catch (Exception ex) {
      log.error("Unexpected error during login for username: {}", loginRequest.username(), ex);
      throw new UnexpectedErrorException(
          "Something went wrong with your login. Please try again later!");
    }

    return (User) userService.loadUserByUsername(loginRequest.username());
  }
}
