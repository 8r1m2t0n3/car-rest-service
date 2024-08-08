package com.brimstone.car.rest.service.exception.keycloak;

import com.brimstone.car.rest.service.exception.model.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class KeycloakUserProvideInvalidCredentialsException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Invalid user credentials provided.";

  private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

  public KeycloakUserProvideInvalidCredentialsException() {
    super(ERROR_MESSAGE.formatted(), STATUS);
  }
}