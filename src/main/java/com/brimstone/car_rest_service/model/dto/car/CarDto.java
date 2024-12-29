package com.brimstone.car_rest_service.model.dto.car;

import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import com.brimstone.car_rest_service.model.enums.DriveType;
import com.brimstone.car_rest_service.model.enums.EngineType;
import com.brimstone.car_rest_service.model.enums.SteeringLocation;
import com.brimstone.car_rest_service.model.enums.TransmissionType;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CarDto {
  private UUID id;
  private String model;
  private BrandDto brand;
  private Year year;
  private BigDecimal price;
  private TransmissionType transmissionType;
  private EngineType engineType;
  private DriveType driveType;
  private SteeringLocation steeringLocation;
  private BigDecimal mileage;
  private Integer color;
  private String vin;
  private String ownerName;
  private Set<CategoryDto> categories;
}
