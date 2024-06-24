package com.foxminded.car_rest_service.model.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CarUpdateDto {
  @NotBlank private String brand;
  @NotBlank private String model;
  @NotNull private Year year;
  // todo: add categories support
  //  private List<Category> categories;
}
