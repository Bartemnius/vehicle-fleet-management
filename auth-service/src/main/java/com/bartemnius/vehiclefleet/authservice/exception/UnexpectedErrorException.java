package com.bartemnius.vehiclefleet.authservice.exception;

public class UnexpectedErrorException extends RuntimeException {
  public UnexpectedErrorException(String message) {
    super(message);
  }
}
