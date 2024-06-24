package com.foxminded.car_rest_service.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CategoryCreationDto {
  private String name;
}
