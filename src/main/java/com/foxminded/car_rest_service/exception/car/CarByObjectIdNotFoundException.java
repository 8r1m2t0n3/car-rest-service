package com.foxminded.car_rest_service.exception.car;

import com.foxminded.car_rest_service.exception.model.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class CarByObjectIdNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Car by objectId: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public CarByObjectIdNotFoundException(String objectId) {
    super(ERROR_MESSAGE.formatted(objectId), STATUS);
  }
}
