package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarSortingOptionsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cars", description = "RESTful API for managing cars")
public interface CarOpenApi {

  @GetMapping
  @Operation(summary = "Get all cars")
  ResponseEntity<List<CarDto>> getAllCars();
  @GetMapping("/sorted")
  @Operation(summary = "Get cars that satisfy the sorting requirements")
  ResponseEntity<List<CarDto>> getSortedCars(
      @Valid @RequestBody CarSortingOptionsDto carSortingOptionsDto);

  @GetMapping("object-id/{objectId}")
  @Operation(summary = "Get car by objectId")
  ResponseEntity<CarDto> getCarByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId);

  @PostMapping
  @Operation(
      summary = "Add car with specified brand, model, year of manufacture and categories",
      security = @SecurityRequirement(name = "bearerAuth"))
  ResponseEntity<CarDto> add(@Valid @RequestBody CarCreationDto carCreationDto);

  @DeleteMapping("/object-id/{objectId}")
  @Operation(
      summary = "Delete car by objectId",
      security = @SecurityRequirement(name = "bearerAuth"))
  ResponseEntity<Void> deleteByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId);

  @DeleteMapping("/id/{id}")
  @Operation(summary = "Delete car by id", security = @SecurityRequirement(name = "bearerAuth"))
  ResponseEntity<Void> deleteById(@Parameter(description = "DB id") @PathVariable Long id);
}
