package com.brimstone.car_rest_service.exception.brand;

import com.brimstone.car_rest_service.exception.model.CustomRuntimeException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class BrandByIdNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Brand by id: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public BrandByIdNotFoundException(UUID brandId) {
    super(ERROR_MESSAGE.formatted(brandId), STATUS);
  }
}
