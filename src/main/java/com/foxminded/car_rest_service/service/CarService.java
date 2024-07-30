package com.foxminded.car_rest_service.service;

import com.foxminded.car_rest_service.model.dto.car.CarCreationDto;
import com.foxminded.car_rest_service.model.dto.car.CarDto;
import com.foxminded.car_rest_service.model.dto.car.CarSortingOptionsDto;
import com.foxminded.car_rest_service.model.dto.car.CarUpdateDto;
import java.util.List;

public interface CarService {

  CarDto save(CarCreationDto carCreationDto);

  List<CarDto> getBySortOptions(CarSortingOptionsDto carSortDto);

  CarDto getByObjectId(String objectId);

  List<CarDto> getAll();

  CarDto update(Long id, CarUpdateDto carUpdateDto);

  void deleteById(Long id);

  void deleteByObjectId(String objectId);
}
