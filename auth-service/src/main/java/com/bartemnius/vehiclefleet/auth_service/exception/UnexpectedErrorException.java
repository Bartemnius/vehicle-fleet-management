package com.bartemnius.vehiclefleet.auth_service.exception;

public class UnexpectedErrorException extends RuntimeException {
  public UnexpectedErrorException(String message) {
    super(message);
  }
}
