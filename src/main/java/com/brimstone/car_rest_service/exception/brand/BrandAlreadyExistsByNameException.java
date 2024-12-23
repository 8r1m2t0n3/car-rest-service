package com.brimstone.car_rest_service.exception.brand;

import com.brimstone.car_rest_service.exception.model.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class BrandAlreadyExistsByNameException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Brand with name: %s already exists.";

  private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public BrandAlreadyExistsByNameException(String brandName) {
    super(ERROR_MESSAGE.formatted(brandName), STATUS);
  }
}
