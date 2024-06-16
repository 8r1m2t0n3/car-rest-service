package com.foxminded.car_rest_service.model.dto;

import com.foxminded.car_rest_service.model.entity.Category;
import java.time.Year;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CarCreationDto {
  private String brand;
  private String model;
  private Year year;
  private List<Category> categories;
}
