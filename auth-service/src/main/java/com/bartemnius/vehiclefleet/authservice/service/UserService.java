package com.bartemnius.vehiclefleet.authservice.service;

import com.bartemnius.vehiclefleet.authservice.dto.UpdateUserRequest;
import com.bartemnius.vehiclefleet.authservice.dto.UserDto;
import com.bartemnius.vehiclefleet.authservice.entity.User;
import com.bartemnius.vehiclefleet.authservice.exception.UserDoesNotExistsException;
import com.bartemnius.vehiclefleet.authservice.repository.UserRepository;
import com.bartemnius.vehiclefleet.authservice.validator.UserValidator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final UserValidator userValidator;

  public UserDto getUser(UUID id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new UserDoesNotExistsException("User with given id does not exists"));
    return mapUserDto(user);
  }

  private UserDto mapUserDto(User user) {
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

  public UserDto updateUser(UUID id, UpdateUserRequest updateRequest) {
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

    User updatedUser = userRepository.save(user);
    return mapUserDto(updatedUser);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username doe not exists"));
  }

  public boolean userExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }
}
