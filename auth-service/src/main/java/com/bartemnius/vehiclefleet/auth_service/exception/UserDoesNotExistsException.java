package com.bartemnius.vehiclefleet.auth_service.exception;

public class UserDoesNotExistsException extends RuntimeException {
  public UserDoesNotExistsException(String message) {
    super(message);
  }
}
