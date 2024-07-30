package com.foxminded.car_rest_service.model.dto.car;

import com.foxminded.car_rest_service.model.pojo.YearRange;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CarSortingOptionsDto {
  private String brand;
  private String model;
  private YearRange manufactureYearRange;
  private List<String> categoryNames;
}
