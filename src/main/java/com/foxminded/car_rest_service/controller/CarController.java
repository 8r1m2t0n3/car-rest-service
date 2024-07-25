package com.foxminded.car_rest_service.controller;

import com.foxminded.car_rest_service.model.dto.car.CarCreationDto;
import com.foxminded.car_rest_service.model.dto.car.CarDto;
import jakarta.validation.Valid;
import java.time.Year;
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
import com.foxminded.car_rest_service.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars", description = "RESTful API for managing cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @GetMapping
  @Operation(summary = "Get all cars")
  public ResponseEntity<List<CarDto>> getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getAll());
  }

  @GetMapping("object-id/{objectId}")
  @Operation(summary = "Get car by objectId")
  public ResponseEntity<CarDto> getCarByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId) {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getByObjectId(objectId));
  }

  @GetMapping("/brand/{brand}")
  @Operation(summary = "Get cars by brand")
  public ResponseEntity<List<CarDto>> getCarsByBrand(
      @Parameter(description = "Brand name") @PathVariable String brand) {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getByBrand(brand));
  }

  @GetMapping("/brand/{brand}/model/{model}")
  @Operation(summary = "Get cars by model")
  public ResponseEntity<List<CarDto>> getByBrandAndModel(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Model name") @PathVariable String model) {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getByBrandAndModel(brand, model));
  }

  @GetMapping("/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by min and max years of manufacture")
  public ResponseEntity<List<CarDto>> getByMinYearAndMaxYear(
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(carService.getByMinYearAndMaxYear(minYear, maxYear));
  }

  @GetMapping("/brand/{brand}/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by brand and min and max years of manufacture")
  public ResponseEntity<List<CarDto>> getByBrandAndMinYearAndMaxYear(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(carService.getByBrandAndMinYearAndMaxYear(brand, minYear, maxYear));
  }

  @GetMapping("/brand/{brand}/model/{model}/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by brand, model and min and max years of manufacture")
  public ResponseEntity<List<CarDto>> getByBrandAndModelAndMinYearAndMaxYear(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Model name") @PathVariable String model,
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(carService.getByBrandAndModelAndMinYearAndMaxYear(brand, model, minYear, maxYear));
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
