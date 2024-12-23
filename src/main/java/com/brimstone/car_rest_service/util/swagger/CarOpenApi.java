package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;

@Tag(name = "Cars", description = "RESTful API for managing cars")
public interface CarOpenApi {

  @Operation(summary = "Creates new car", security = @SecurityRequirement(name = "bearerAuth"))
  CarDto createCar(CarCreationDto carCreationDto);

  @Operation(summary = "Deletes car by id", security = @SecurityRequirement(name = "bearerAuth"))
  void deleteCarById(UUID id);

  @Operation(summary = "Retrieves all cars")
  List<CarDto> getAllCars();

  @Operation(summary = "Retrieves car by id")
  CarDto getCarById(UUID id);
}
