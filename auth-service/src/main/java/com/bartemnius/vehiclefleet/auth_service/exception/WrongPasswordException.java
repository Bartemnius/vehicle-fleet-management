package com.bartemnius.vehiclefleet.auth_service.exception;

public class WrongPasswordException extends RuntimeException {
  public WrongPasswordException(String message) {
    super(message);
  }
}
