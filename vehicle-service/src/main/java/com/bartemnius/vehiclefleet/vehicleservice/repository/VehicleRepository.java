package com.bartemnius.vehiclefleet.vehicleservice.repository;

import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
