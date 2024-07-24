package com.foxminded.car_rest_service.model.dto.car;

import com.foxminded.car_rest_service.model.dto.category.CategoryCreationDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Year;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarCreationDto {
  private String objectId;
  @NotBlank private String brand;
  @NotBlank private String model;
  @NotNull private Year year;
  @NotEmpty private List<CategoryCreationDto> categories;
}
