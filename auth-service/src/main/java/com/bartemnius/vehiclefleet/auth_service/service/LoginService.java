package com.bartemnius.vehiclefleet.auth_service.service;

import com.bartemnius.vehiclefleet.auth_service.dto.LoginRequest;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.exception.UserDoesNotExistsException;
import com.bartemnius.vehiclefleet.auth_service.exception.WrongPasswordException;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
  private final UserRepository userRepository;

  public void validateLogin(LoginRequest loginRequest) {
    User user =
        userRepository
            .findByUsername(loginRequest.username())
            .orElseThrow(
                () -> new UserDoesNotExistsException("User with this username does not exists!"));
    checkPassword(user.getPassword(), loginRequest.password());
    log.info("Login successful!");
  }

  private void checkPassword(String password, String providedPassword) {
    if (!password.equals(providedPassword)) {
      throw new WrongPasswordException("Provided password is not correct!");
    }
  }
}
