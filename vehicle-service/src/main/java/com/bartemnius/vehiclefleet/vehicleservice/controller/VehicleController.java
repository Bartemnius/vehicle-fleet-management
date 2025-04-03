package com.bartemnius.vehiclefleet.vehicleservice.controller;

import com.bartemnius.vehiclefleet.vehicleservice.dto.Response;
import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.service.VehicleService;
import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VehicleController {
  private final VehicleService vehicleService;

  // ================= ADMIN =================

  @GetMapping("/admin/vehicles")
  public ResponseEntity<Response<List<VehicleDto>>> getAllVehicles() {
    List<VehicleDto> vehicles = vehicleService.getAllVehicles();
    return ResponseEntity.ok(new Response<>("All vehicles retrieved", vehicles));
  }

  @PostMapping("/admin/vehicles")
  public ResponseEntity<Response<VehicleDto>> addVehicle(@RequestBody VehicleDto vehicleDto) {
    VehicleDto createdVehicle = vehicleService.addVehicle(vehicleDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response<>("Vehicle created successfully", createdVehicle));
  }

  @GetMapping("/admin/vehicles/{vin}")
  public ResponseEntity<Response<VehicleDto>> getVehicle(@PathVariable String vin) {
    VehicleDto vehicle = vehicleService.getVehicle(vin);
    return ResponseEntity.ok(new Response<>("Vehicle retrieved successfully", vehicle));
  }

  @PutMapping("/admin/vehicles/{vin}")
  public ResponseEntity<Response<VehicleDto>> updateVehicle(
      @PathVariable String vin, @RequestBody VehicleDto vehicleDto) {
    VehicleDto updatedVehicle = vehicleService.updateVehicle(vin, vehicleDto);
    return ResponseEntity.ok(new Response<>("Vehicle updated successfully", updatedVehicle));
  }

  @DeleteMapping("/admin/vehicles/{vin}")
  public ResponseEntity<Response<String>> deleteVehicle(@PathVariable String vin) {
    vehicleService.deleteVehicle(vin);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .body(new Response<>("Vehicle deleted successfully", vin));
  }

  @GetMapping("/admin/vehicles/status/{status}")
  public ResponseEntity<Response<List<VehicleDto>>> getVehiclesByStatus(
      @PathVariable VehicleStatus status) {
    List<VehicleDto> vehicles = vehicleService.getVehiclesByStatus(status);
    return ResponseEntity.ok(new Response<>("Vehicles retrieved successfully", vehicles));
  }

  // ================= USER =================

  @GetMapping("/user/vehicles")
  public ResponseEntity<Response<List<VehicleDto>>> getUserVehicles(
      @AuthenticationPrincipal String username) {
    UUID userId = vehicleService.getUserIdFromAuthService(username);
    List<VehicleDto> vehicles = vehicleService.getUserVehicles(userId);
    return ResponseEntity.ok(new Response<>("User's vehicles retrieved", vehicles));
  }

  @GetMapping("/user/vehicles/available")
  public ResponseEntity<Response<List<VehicleDto>>> getAvailableVehicles() {
    List<VehicleDto> vehicles = vehicleService.getAvailableVehicles();
    return ResponseEntity.ok(new Response<>("Available vehicles retrieved", vehicles));
  }

  @PutMapping("/user/vehicles/{vin}/assign")
  public ResponseEntity<Response<VehicleDto>> assignVehicleToUser(
      @AuthenticationPrincipal String username, @PathVariable String vin) {
    UUID userId = vehicleService.getUserIdFromAuthService(username);
    VehicleDto assignedVehicle = vehicleService.assignVehicleToUser(vin, userId);
    return ResponseEntity.ok(new Response<>("Vehicle assigned successfully", assignedVehicle));
  }
}
