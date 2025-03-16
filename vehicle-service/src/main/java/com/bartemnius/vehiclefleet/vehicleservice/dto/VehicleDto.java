package com.bartemnius.vehiclefleet.vehicleservice.dto;

import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleDto(
    Long id,
    @NotBlank(message = "VIN cannot be blank") String vin,
    @NotBlank(message = "Brand cannot be blank") String brand,
    @NotBlank(message = "Model cannot be blank") String model,
    @NotNull(message = "Year cannot be null") @Min(value = 1886, message = "Year must be valid")
        Integer year,
    @NotNull(message = "Status cannot be null") VehicleStatus status,
    Long userId) {}
