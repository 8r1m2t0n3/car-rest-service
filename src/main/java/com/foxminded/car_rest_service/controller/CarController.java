package com.foxminded.car_rest_service.controller;

import com.foxminded.car_rest_service.model.dto.CarCreationDto;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.model.entity.Category;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars", description = "Methods for working with cars")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;
  private final CategoryService categoryService;

  @GetMapping
  @Operation(summary = "Get all cars")
	public List<Car> getAll() {
    return carService.getAll();
  }

  @GetMapping("object-id/{objectId}")
  @Operation(summary = "Get car by objectId")
	public Optional<Car> getCarByObjectId(
      @Parameter(description = "Unique object id") @PathVariable String objectId) {
    return carService.getByObjectId(objectId);
  }

  @GetMapping("/brand/{brand}")
  @Operation(summary = "Get cars by brand")
	public List<Car> getCarsByBrand(@Parameter(description = "Brand name") @PathVariable String brand) {
    return carService.getByBrand(brand);
  }

  @GetMapping("/brand/{brand}/model/{model}")
  @Operation(summary = "Get cars by model")
	public List<Car> getByBrandAndModel(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Model name") @PathVariable String model) {
    return carService.getByBrandAndModel(brand, model);
  }

  @GetMapping("/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by min and max years of manufacture")
	public List<Car> getByMinYearAndMaxYear(
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    return carService.getByMinYearAndMaxYear(minYear, maxYear);
  }

  @GetMapping("/brand/{brand}/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by brand and min and max years of manufacture")
	public List<Car> getByBrandAndMinYearAndMaxYear(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    List<Car> cars = carService.getByBrand(brand);
    cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
    return cars;
  }

  @GetMapping("/brand/{brand}/model/{model}/min-year/{minYear}/max-year/{maxYear}")
  @Operation(summary = "Get cars by brand, model and min and max years of manufacture")
	public List<Car> getByBrandAndModelAndMinYearAndMaxYear(
      @Parameter(description = "Brand name") @PathVariable String brand,
      @Parameter(description = "Model name") @PathVariable String model,
      @Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
      @Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
    List<Car> cars = carService.getByBrand(brand);
    cars.removeIf(o -> !o.getModel().equals(model));
    cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
    return cars;
  }

  @PostMapping
  @Operation(
      summary = "Add car with specified brand, model, year of manufacture and categories",
      security = @SecurityRequirement(name = "bearerAuth"))
  public Car add(@RequestBody CarCreationDto carCreationDto) {
    return carService.save(carCreationDto);
  }

  @DeleteMapping
  @Operation(
      summary = "Delete car by objectId",
      security = @SecurityRequirement(name = "bearerAuth"))
	public void delete(
      @Parameter(description = "Unique object id") @RequestParam("object-id") UUID objectId) {
    carService.deleteByObjectId(objectId);
  }
}
