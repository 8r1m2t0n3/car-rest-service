package com.brimstone.car_rest_service.model.dto.brand;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BrandDto {
  private UUID id;
  private String name;
}
