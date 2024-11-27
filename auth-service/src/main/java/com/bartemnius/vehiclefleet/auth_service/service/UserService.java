package com.bartemnius.vehiclefleet.auth_service.service;

import com.bartemnius.vehiclefleet.auth_service.dto.UpdateUserRequest;
import com.bartemnius.vehiclefleet.auth_service.dto.UserDto;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.exception.UserDoesNotExistsException;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import com.bartemnius.vehiclefleet.auth_service.validator.UserValidator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository userRepository;
  private final UserValidator userValidator;

  public UserDto getUser(UUID id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new UserDoesNotExistsException("User with given id does not exists"));
    return mapUsertDto(user);
  }

  private UserDto mapUsertDto(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPhoneNumber(),
        user.getRole(),
        user.isTwoFactorEnabled(),
        user.getTwoFactorMethod());
  }

  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }

  public void updateUser(UUID id, UpdateUserRequest updateRequest) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new UserDoesNotExistsException("User with given id does not exists"));

    userValidator.validate(updateRequest);

    if (updateRequest.getUsername() != null) {
      user.setUsername(updateRequest.getUsername());
    }

    if (updateRequest.getEmail() != null) {
      user.setEmail(updateRequest.getEmail());
    }

    if (updateRequest.getPhoneNumber() != null) {
      user.setPhoneNumber(updateRequest.getPhoneNumber());
    }

    userRepository.save(user);
  }
}
