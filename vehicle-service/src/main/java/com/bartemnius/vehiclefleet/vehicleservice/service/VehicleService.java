package com.bartemnius.vehiclefleet.vehicleservice.service;

import com.bartemnius.vehiclefleet.vehicleservice.dto.ReportEventDto;
import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.entity.Vehicle;
import com.bartemnius.vehiclefleet.vehicleservice.exception.VehicleNotFoundException;
import com.bartemnius.vehiclefleet.vehicleservice.repository.VehicleRepository;
import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {
  private final VehicleRepository vehicleRepository;
  private final WebClient webClient;
  private final VehicleEventPublisher vehicleEventPublisher;

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

  public List<VehicleDto> getUserVehicles(UUID userId) {
    return mapToDtos(vehicleRepository.findByUserId(userId));
  }

  public List<VehicleDto> getAvailableVehicles() {
    return mapToDtos(vehicleRepository.findByUserIdIsNull());
  }

  public VehicleDto assignVehicleToUser(String vin, UUID userId) {
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

    vehicleEventPublisher.sendVehicleEvent(new ReportEventDto(
            updatedVehicle.getVin(),
            userId.toString(),
            "ASSIGNED",
            LocalDateTime.now()));

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

  public UUID getUserIdFromAuthService(String username) {
    try {
      log.info("I into getUserIdFromAuthService 2");
      String token = getCurrentToken();
      if (token == null) {
        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Missing token in SecurityContext");
      }

      Map<String, Object> response =
          webClient
              .get()
              .uri(
                  uriBuilder ->
                      uriBuilder.path("/auth/user-id").queryParam("username", username).build())
              .header("Authorization", "Bearer " + token)
              .retrieve()
              .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
              .block();

      if (response == null || !response.containsKey("userId")) {
        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "User ID not found for: " + username);
      }

      return UUID.fromString((String) response.get("userId"));

    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch user ID", e);
    }
  }

  private String getCurrentToken() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getCredentials() instanceof String token) {
      return token;
    }
    return null;
  }
}
