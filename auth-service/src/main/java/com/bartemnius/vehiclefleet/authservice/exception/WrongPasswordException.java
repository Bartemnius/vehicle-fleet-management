package com.bartemnius.vehiclefleet.authservice.exception;

public class WrongPasswordException extends RuntimeException {
  public WrongPasswordException(String message) {
    super(message);
  }
}
