package com.bartemnius.vehiclefleet.vehicleservice.controller;

import com.bartemnius.vehiclefleet.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
}
