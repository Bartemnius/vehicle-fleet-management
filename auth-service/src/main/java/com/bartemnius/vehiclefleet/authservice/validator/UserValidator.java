package com.bartemnius.vehiclefleet.authservice.validator;

import com.bartemnius.vehiclefleet.authservice.dto.RegistrationRequest;
import com.bartemnius.vehiclefleet.authservice.dto.UpdateUserRequest;
import com.bartemnius.vehiclefleet.authservice.exception.UserAlreadyExistsException;
import com.bartemnius.vehiclefleet.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

  private final UserRepository userRepository;

  public void validate(RegistrationRequest request) {
    checkUniqueEmail(request.getEmail());
    checkUniqueUsername(request.getUsername());
    checkUniquePhoneNumber(request.getPhoneNumber());
  }

  public void validate(UpdateUserRequest request) {
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
