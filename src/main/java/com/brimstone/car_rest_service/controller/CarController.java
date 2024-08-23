package com.brimstone.car_rest_service.controller;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarSortingOptionsDto;
import com.brimstone.car_rest_service.service.CarService;
import com.brimstone.car_rest_service.util.swagger.CarOpenApi;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CarController implements CarOpenApi {

  private final CarService carService;

  @GetMapping
  public ResponseEntity<List<CarDto>> getAllCars() {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getAll());
  }

  @GetMapping("/sorted")
  public ResponseEntity<List<CarDto>> getSortedCars(
      @Valid @RequestBody CarSortingOptionsDto carSortingOptionsDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(carService.getBySortOptions(carSortingOptionsDto));
  }

  @GetMapping("object-id/{objectId}")
  public ResponseEntity<CarDto> getCarByObjectId(@PathVariable String objectId) {
    return ResponseEntity.status(HttpStatus.OK).body(carService.getByObjectId(objectId));
  }

  @PostMapping
  public ResponseEntity<CarDto> add(@Valid @RequestBody CarCreationDto carCreationDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carCreationDto));
  }

  @DeleteMapping("/object-id/{objectId}")
  public ResponseEntity<Void> deleteByObjectId(@PathVariable String objectId) {
    carService.deleteByObjectId(objectId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    carService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
