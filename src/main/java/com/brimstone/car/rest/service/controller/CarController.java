package com.brimstone.car.rest.service.controller;

import com.brimstone.car.rest.service.model.dto.car.CarCreationDto;
import com.brimstone.car.rest.service.model.dto.car.CarDto;
import com.brimstone.car.rest.service.model.dto.car.CarSortingOptionsDto;
import com.brimstone.car.rest.service.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/car")
@Tag(name = "Cars", description = "RESTful API for managing cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @GetMapping
  @Operation(summary = "Get all cars")
  public ResponseEntity<List<CarDto>> getAllCars() {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getAll());
  }

  @GetMapping("/sorted")
  @Operation(summary = "Get cars that satisfy the sorting requirements")
  public ResponseEntity<List<CarDto>> getSortedCars(
      @Valid @RequestBody CarSortingOptionsDto carSortingOptionsDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(carService.getBySortOptions(carSortingOptionsDto));
  }

  @GetMapping("object-id/{objectId}")
  @Operation(summary = "Get car by objectId")
  public ResponseEntity<CarDto> getCarByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId) {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getByObjectId(objectId));
  }

  @PostMapping
  @Operation(
      summary = "Add car with specified brand, model, year of manufacture and categories",
      security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<CarDto> add(@Valid @RequestBody CarCreationDto carCreationDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carCreationDto));
  }

  @DeleteMapping("/object-id/{objectId}")
  @Operation(
      summary = "Delete car by objectId",
      security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> deleteByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId) {
    carService.deleteByObjectId(objectId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/id/{id}")
  @Operation(summary = "Delete car by id", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> deleteById(@Parameter(description = "DB id") @PathVariable Long id) {
    carService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
