package com.bartemnius.vehiclefleet.vehicleservice.service;

import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.exception.VehicleNotFoundException;
import com.bartemnius.vehiclefleet.vehicleservice.repository.VehicleRepository;
import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
  private final VehicleRepository vehicleRepository;

  public List<VehicleDto> getAllVehicles() {
    return mapToDtos(vehicleRepository.findAll());
  }

  public VehicleDto getVehicle(String vin) {
    return vehicleRepository
        .findByVin(vin)
        .map(this::mapToDto)
        .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given VIN does not exist!"));
  }

  public VehicleDto addVehicle(VehicleDto vehicleDto) {
    Vehicle vehicle = new Vehicle();
    vehicle.setVin(vehicleDto.vin());
    vehicle.setBrand(vehicleDto.brand());
    vehicle.setModel(vehicleDto.model());
    vehicle.setYear(vehicleDto.year());
    vehicle.setStatus(vehicleDto.status());
    vehicle.setUserId(vehicleDto.userId());

    Vehicle savedVehicle = vehicleRepository.save(vehicle);
    return mapToDto(savedVehicle);
  }

  public VehicleDto updateVehicle(String vin, VehicleDto vehicleDto) {
    Vehicle vehicle =
        vehicleRepository
            .findByVin(vin)
            .orElseThrow(
                () -> new VehicleNotFoundException("Vehicle with given VIN does not exist!"));

    vehicle.setBrand(vehicleDto.brand());
    vehicle.setModel(vehicleDto.model());
    vehicle.setYear(vehicleDto.year());
    vehicle.setStatus(vehicleDto.status());

    Vehicle updatedVehicle = vehicleRepository.save(vehicle);
    return mapToDto(updatedVehicle);
  }

  public void deleteVehicle(String vin) {
    Vehicle vehicle =
        vehicleRepository
            .findByVin(vin)
            .orElseThrow(
                () -> new VehicleNotFoundException("Vehicle with given VIN does not exist!"));
    vehicleRepository.delete(vehicle);
  }

  public List<VehicleDto> getVehiclesByStatus(VehicleStatus status) {
    return mapToDtos(vehicleRepository.findByStatus(status));
  }

  public List<VehicleDto> getUserVehicles(Long userId) {
    return mapToDtos(vehicleRepository.findByUserId(userId));
  }

  public List<VehicleDto> getAvailableVehicles() {
    return mapToDtos(vehicleRepository.findByUserIdIsNull());
  }

  public VehicleDto assignVehicleToUser(String vin, Long userId) {
    Vehicle vehicle =
        vehicleRepository
            .findByVin(vin)
            .orElseThrow(
                () -> new VehicleNotFoundException("Vehicle with given VIN does not exist!"));

    if (vehicle.getUserId() != null) {
      throw new IllegalStateException("Vehicle is already assigned to a user.");
    }

    vehicle.setUserId(userId);
    Vehicle updatedVehicle = vehicleRepository.save(vehicle);
    return mapToDto(updatedVehicle);
  }

  private VehicleDto mapToDto(Vehicle vehicle) {
    return new VehicleDto(
        vehicle.getId(),
        vehicle.getVin(),
        vehicle.getBrand(),
        vehicle.getModel(),
        vehicle.getYear(),
        vehicle.getStatus(),
        vehicle.getUserId());
  }

  private List<VehicleDto> mapToDtos(List<Vehicle> vehicles) {
    return vehicles.stream().map(this::mapToDto).toList();
  }
}
