package com.brimstone.car_rest_service.model.dto.category;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CategoryDto {
  private UUID id;
  private String name;
}
