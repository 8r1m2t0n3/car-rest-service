package com.foxminded.car_rest_service.service;

import com.foxminded.car_rest_service.model.dto.car.CarCreationDto;
import com.foxminded.car_rest_service.model.dto.car.CarDto;
import com.foxminded.car_rest_service.model.dto.car.CarUpdateDto;
import java.time.Year;
import java.util.List;

public interface CarService {

  CarDto save(CarCreationDto carCreationDto);

  CarDto getById(Long id);

  CarDto getByObjectId(String objectId);

  List<CarDto> getByBrand(String brand);

  List<CarDto> getByBrandAndModel(String brand, String model);

  List<CarDto> getByMinYearAndMaxYear(Year minYear, Year maxYear);

  List<CarDto> getByBrandAndMinYearAndMaxYear(String brand, Year minYear, Year maxYear);

  List<CarDto> getByBrandAndModelAndMinYearAndMaxYear(
      String brand, String model, Year minYear, Year maxYear);

  List<CarDto> getAll();

  CarDto update(Long id, CarUpdateDto carUpdateDto);

  void deleteById(Long id);

  void deleteByObjectId(String objectId);

  void bindCategoriesWithCarAndSave(CarCreationDto carCreationDto);
}
