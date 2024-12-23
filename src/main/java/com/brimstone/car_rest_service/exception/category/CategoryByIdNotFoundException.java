package com.brimstone.car_rest_service.exception.category;

import com.brimstone.car_rest_service.exception.model.CustomRuntimeException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class CategoryByIdNotFoundException extends CustomRuntimeException {

  private static final String ERROR_MESSAGE = "Category by id: %s not found.";

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public CategoryByIdNotFoundException(UUID categoryId) {
    super(ERROR_MESSAGE.formatted(categoryId), STATUS);
  }
}
