package com.bartemnius.vehiclefleet.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * General class for response. It makes returning data as a json easier
 *
 * @param <T> data in any kind or form will be presented in json format
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
  private String message;
  private T data;
}
