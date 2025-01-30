package com.bartemnius.vehiclefleet.authservice.exception;

import com.bartemnius.vehiclefleet.authservice.dto.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<Response<Void>> handleUserAlreadyExistsException(
      UserAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response<>(ex.getMessage(), null));
  }

  @ExceptionHandler(UserDoesNotExistsException.class)
  public ResponseEntity<Response<Void>> handleUserDoesNotExistsException(
      UserDoesNotExistsException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(ex.getMessage(), null));
  }

  @ExceptionHandler(WrongPasswordException.class)
  public ResponseEntity<Response<Void>> handleWrongPasswordException(WrongPasswordException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new Response<>(ex.getMessage(), null));
  }

  // for @Valid annotation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response<List<String>>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

    return ResponseEntity.badRequest().body(new Response<>("Validation failed", errors));
  }

  @ExceptionHandler(UnexpectedErrorException.class)
  public ResponseEntity<Response<Void>> handleUnexpectedErrorException(
      UnexpectedErrorException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new Response<>(ex.getMessage(), null));
  }
}
