package com.bartemnius.vehiclefleet.vehicleservice.service;

import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> all = vehicleRepository.findAll();
        return mapToDtos(all);
    }

    private List<VehicleDto> mapToDtos(List<Vehicle> vehicles) {
        return vehicles.stream().map(
                vehicle ->
                        new VehicleDto(
                                vehicle.getId(),
                                vehicle.getVin(),
                                vehicle.getBrand(),
                                vehicle.getModel(),
                                vehicle.getYear(),
                                vehicle.getStatus()
        )).toList();
    }
}
