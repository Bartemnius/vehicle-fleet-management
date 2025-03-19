package com.bartemnius.vehiclefleet.vehicleservice.repository;

import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
  Optional<Vehicle> findByVin(String vin);

  List<Vehicle> findByStatus(VehicleStatus status);

  List<Vehicle> findByUserId(UUID userId);

  List<Vehicle> findByUserIdIsNull();
}
