package com.brimstone.car.rest.service.exception.category;

import com.brimstone.car.rest.service.exception.model.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class CategoryByNameNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Category by name: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public CategoryByNameNotFoundException(String categoryName) {
    super(ERROR_MESSAGE.formatted(categoryName), STATUS);
  }
}
