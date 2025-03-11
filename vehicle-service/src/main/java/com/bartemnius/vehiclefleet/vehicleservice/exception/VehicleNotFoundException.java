package com.bartemnius.vehiclefleet.vehicleservice.exception;

public class VehicleNotFoundException extends RuntimeException {
  public VehicleNotFoundException(String message) {
    super(message);
  }
}
