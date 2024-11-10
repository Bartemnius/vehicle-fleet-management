package com.bartemnius.vehiclefleet.auth_service.service;

import com.bartemnius.vehiclefleet.auth_service.dto.RegisterRequest;
import com.bartemnius.vehiclefleet.auth_service.entity.User;
import com.bartemnius.vehiclefleet.auth_service.exception.UserAlreadyExistsException;
import com.bartemnius.vehiclefleet.auth_service.repository.UserRepository;
import com.bartemnius.vehiclefleet.auth_service.utils.Role;
import com.bartemnius.vehiclefleet.auth_service.utils.TwoFactorMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public void registerUser(RegisterRequest registerRequest) {
        if(userExists(registerRequest)) {
            throw new UserAlreadyExistsException("User with provided email already exists!");
        }

        //TODO: create mappings
        userRepository.save(new User(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber(),
                Role.USER,
                false,
                TwoFactorMethod.EMAIL
        ));
    }

    private boolean userExists(RegisterRequest registerRequest) {
        return userRepository.findByEmail(registerRequest.getEmail()).isPresent();
    }
}
