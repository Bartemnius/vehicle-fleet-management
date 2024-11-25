package com.bartemnius.vehiclefleet.auth_service.service;

import com.bartemnius.vehiclefleet.auth_service.dto.UserDto;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.exception.UserDoesNotExistsException;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    public UserDto getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistsException("User with given id does not exists"));
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
}
