package com.brimstone.car_rest_service.controller;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.service.CarService;
import com.brimstone.car_rest_service.util.swagger.CarOpenApi;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController implements CarOpenApi {

  private final CarService carService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarDto createCar(@RequestBody @Valid CarCreationDto carCreationDto) {
    return carService.save(carCreationDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCarById(@RequestParam UUID id) {
    carService.deleteById(id);
  }

  @GetMapping
  public List<CarDto> getAllCars() {
    return carService.getAll();
  }

  @GetMapping("/{id}")
  public CarDto getCarById(@PathVariable UUID id) {
    return carService.getById(id);
  }
}
