package com.bartemnius.vehiclefleet.vehicleservice.repository;

import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVin(String vin);

    List<Vehicle> findByStatus(VehicleStatus status);
}
