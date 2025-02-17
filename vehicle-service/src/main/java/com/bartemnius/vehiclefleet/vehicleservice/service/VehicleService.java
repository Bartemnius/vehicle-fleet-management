package com.bartemnius.vehiclefleet.vehicleservice.service;

import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.exception.VehicleNotFoundException;
import com.bartemnius.vehiclefleet.vehicleservice.repository.VehicleRepository;
import java.util.List;

import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import org.springframework.stereotype.Service;

@Service
// @RequiredArgsConstructor
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
    return vehicles.stream()
        .map(
            vehicle ->
                new VehicleDto(
                    vehicle.getId(),
                    vehicle.getVin(),
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getYear(),
                    vehicle.getStatus()))
        .toList();
  }

    public VehicleDto getVehicle(String vin) {
      Vehicle vehicle = vehicleRepository.findByVin(vin)
              .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given vin number does not exists!"));
      return mapToDtos(List.of(vehicle)).get(0);
    }

  public VehicleDto updateVehicle(String vin, VehicleDto vehicleDto) {
    Vehicle vehicle = vehicleRepository.findByVin(vin)
            .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given vin number does not exists!"));

    vehicle.setBrand(vehicleDto.brand());
    vehicle.setModel(vehicleDto.model());
    vehicle.setYear(vehicleDto.year());
    vehicle.setStatus(vehicleDto.status());

    vehicleRepository.save(vehicle);
    return mapToDtos(List.of(vehicle)).get(0);
  }

  public void deleteVehicle(String vin) {
    Vehicle vehicle = vehicleRepository.findByVin(vin)
            .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given vin number does not exists!"));
    vehicleRepository.delete(vehicle);
  }

  public List<VehicleDto> getVehiclesByStatus(VehicleStatus status) {
    List<Vehicle> vehicles = vehicleRepository.findByStatus(status);
    return mapToDtos(vehicles);
  }
}
