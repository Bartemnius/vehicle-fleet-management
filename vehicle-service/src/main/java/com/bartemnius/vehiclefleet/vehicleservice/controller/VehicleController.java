package com.bartemnius.vehiclefleet.vehicleservice.controller;

import com.bartemnius.vehiclefleet.vehicleservice.dto.Response;
import com.bartemnius.vehiclefleet.vehicleservice.dto.VehicleDto;
import com.bartemnius.vehiclefleet.vehicleservice.service.VehicleService;

import java.util.List;

import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping()
    public ResponseEntity<Response<List<VehicleDto>>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        Response<List<VehicleDto>> response =
                new Response<>("Retrieved vehicles successfully", vehicles);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Response<VehicleDto>> getVehicle(@PathVariable String vin) {
      VehicleDto vehicle = vehicleService.getVehicle(vin);
      return ResponseEntity.ok().body(new Response<>("Vehicle retrieved successfully", vehicle));
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Response<VehicleDto>> updateVehicle(@PathVariable String vin, @RequestBody VehicleDto vehicleDto) {
        VehicleDto updatedVehicle = vehicleService.updateVehicle(vin, vehicleDto);
        return ResponseEntity.ok().body(new Response<>("Vehicle updated successfully", updatedVehicle));
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Response<String>> deleteVehicle(@PathVariable String vin) {
        vehicleService.deleteVehicle(vin);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response<>("Vehicle deleted successfully", vin));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Response<List<VehicleDto>>> getVehiclesByStatus(@PathVariable VehicleStatus status) {
        List<VehicleDto> vehicles = vehicleService.getVehiclesByStatus(status);
        return ResponseEntity.ok().body(new Response<>("Vehicles retrieved successfully", vehicles));
    }



}
