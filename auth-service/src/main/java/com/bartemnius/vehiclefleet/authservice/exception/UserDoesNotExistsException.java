package com.bartemnius.vehiclefleet.authservice.exception;

public class UserDoesNotExistsException extends RuntimeException {
  public UserDoesNotExistsException(String message) {
    super(message);
  }
}
