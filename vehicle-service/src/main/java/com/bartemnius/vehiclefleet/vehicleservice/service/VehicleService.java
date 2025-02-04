package com.bartemnius.vehiclefleet.vehicleservice.service;

import com.bartemnius.vehiclefleet.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
}
