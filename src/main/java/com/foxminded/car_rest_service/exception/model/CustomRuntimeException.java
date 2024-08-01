package com.foxminded.car_rest_service.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base class for custom RuntimeException in the application. All custom RuntimeException classes
 * should extend this class.
 */
@Getter
public abstract class CustomRuntimeException extends RuntimeException {

  protected String exceptionMessage;

  protected HttpStatus httpStatus;

  public CustomRuntimeException(Throwable cause, HttpStatus httpStatus) {
    super(cause);
    this.httpStatus = httpStatus;
  }

  public CustomRuntimeException(String message, HttpStatus httpStatus) {
    super(message);
    this.exceptionMessage = message;
    this.httpStatus = httpStatus;
  }

  public CustomRuntimeException(String message, Throwable cause, HttpStatus httpStatus) {
    super(message, cause);
    this.exceptionMessage = message;
    this.httpStatus = httpStatus;
  }
}
