package com.brimstone.car_rest_service.service;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarUpdateDto;
import java.util.List;
import java.util.UUID;

public interface CarService {
  CarDto save(CarCreationDto carCreationDto);
  List<CarDto> getAll();
  CarDto getById(UUID id);
  CarDto update(UUID id, CarUpdateDto carUpdateDto);
  void deleteById(UUID id);
}
