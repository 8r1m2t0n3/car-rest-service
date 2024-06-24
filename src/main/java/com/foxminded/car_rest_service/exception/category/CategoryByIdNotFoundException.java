package com.foxminded.car_rest_service.exception.category;

import com.foxminded.car_rest_service.exception.model.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class CategoryByIdNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Category by objectId: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public CategoryByIdNotFoundException(Long categoryId) {
    super(ERROR_MESSAGE.formatted(categoryId), STATUS);
  }
}
