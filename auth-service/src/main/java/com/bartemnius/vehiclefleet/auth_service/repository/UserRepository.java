package com.bartemnius.vehiclefleet.auth_service.repository;

import com.bartemnius.vehiclefleet.auth_service.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  Optional<User> findByPhoneNumber(String phoneNumber);
}
