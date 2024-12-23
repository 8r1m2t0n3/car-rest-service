package com.brimstone.car_rest_service.exception.car;

import com.brimstone.car_rest_service.exception.model.CustomRuntimeException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class CarByIdNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Car by id: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public CarByIdNotFoundException(UUID carId) {
    super(ERROR_MESSAGE.formatted(carId), STATUS);
  }
}
