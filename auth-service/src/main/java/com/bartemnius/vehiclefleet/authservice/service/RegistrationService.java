package com.bartemnius.vehiclefleet.authservice.service;

import com.bartemnius.vehiclefleet.authservice.dto.RegistrationRequest;
import com.bartemnius.vehiclefleet.authservice.entity.User;
import com.bartemnius.vehiclefleet.authservice.repository.UserRepository;
import com.bartemnius.vehiclefleet.authservice.utils.Role;
import com.bartemnius.vehiclefleet.authservice.utils.TwoFactorMethod;
import com.bartemnius.vehiclefleet.authservice.validator.UserValidator;
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
  private final UserValidator userValidator;

  public void registerUser(RegistrationRequest registrationRequest) {
    userValidator.validate(registrationRequest);

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
}
