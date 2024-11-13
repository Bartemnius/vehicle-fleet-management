package com.bartemnius.vehiclefleet.auth_service.service;

import com.bartemnius.vehiclefleet.auth_service.dto.RegistrationRequest;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.exception.UserAlreadyExistsException;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import com.bartemnius.vehiclefleet.auth_service.utils.Role;
import com.bartemnius.vehiclefleet.auth_service.utils.TwoFactorMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void registerUser(RegistrationRequest registrationRequest) {
    validate(registrationRequest);

    // TODO: create mappings
    String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
    userRepository.save(
        new User(
            registrationRequest.getUsername(),
            encodedPassword,
            registrationRequest.getEmail(),
            registrationRequest.getPhoneNumber(),
            Role.USER,
            false,
            TwoFactorMethod.EMAIL));
    log.info("User with name {} is now registered", registrationRequest.getUsername());
  }

  public void validate(RegistrationRequest request) {
    checkUniqueEmail(request.getEmail());
    checkUniqueUsername(request.getUsername());
    checkUniquePhoneNumber(request.getPhoneNumber());
  }

  private void checkUniqueEmail(String email) {
    if (userRepository.findByEmail(email).isPresent()) {
      throw new UserAlreadyExistsException("User with this email already registered!");
    }
  }

  private void checkUniqueUsername(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new UserAlreadyExistsException("Username already in use!");
    }
  }

  private void checkUniquePhoneNumber(String phoneNumber) {
    if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
      throw new UserAlreadyExistsException("User with this phone number is already registered!");
    }
  }
}
