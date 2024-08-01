package com.brimstone.car.rest.service.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CategoryDto {
  private Long id;
  private String name;
}
