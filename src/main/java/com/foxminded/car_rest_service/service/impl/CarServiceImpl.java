package com.foxminded.car_rest_service.service.impl;

import com.foxminded.car_rest_service.exception.car.CarByIdNotFoundException;
import com.foxminded.car_rest_service.exception.car.CarByObjectIdNotFoundException;
import com.foxminded.car_rest_service.exception.category.CategoryByNameNotFoundException;
import com.foxminded.car_rest_service.model.dto.car.CarCreationDto;
import com.foxminded.car_rest_service.model.dto.car.CarDto;
import com.foxminded.car_rest_service.model.dto.car.CarUpdateDto;
import com.foxminded.car_rest_service.model.dto.category.CategoryCreationDto;
import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.repository.CarRepository;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;
import com.foxminded.car_rest_service.util.SequenceGenerator;
import com.foxminded.car_rest_service.util.mapper.CarMapper;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final CategoryService categoryService;
  private final CarMapper carMapper;

  @Value("${object-id.length}")
  private int objectIdLength;

  @Transactional
  @Override
  public CarDto save(CarCreationDto carCreationDto) {
    Car car = carMapper.toEntity(carCreationDto);

    if (Optional.ofNullable(carCreationDto.getObjectId()).isEmpty()) {
      car.setObjectId(SequenceGenerator.generate(objectIdLength));
    }

    carRepository.save(car);
    return carMapper.toDto(car);
  }

  @Override
  public CarDto getById(Long id) {
    return carMapper.toDto(
        carRepository.findById(id).orElseThrow(() -> new CarByIdNotFoundException(id)));
  }

  @Override
  public CarDto getByObjectId(String objectId) {
    return carMapper.toDto(
        carRepository
            .findByObjectId(objectId)
            .orElseThrow(() -> new CarByObjectIdNotFoundException(objectId)));
  }

  @Override
  public List<CarDto> getByBrand(String brand) {
    return carRepository.findByBrand(brand).stream().map(carMapper::toDto).toList();
  }

  @Override
  public List<CarDto> getByBrandAndModel(String brand, String model) {
    return carRepository.findByBrandAndModel(brand, model).stream().map(carMapper::toDto).toList();
  }

  @Override
  public List<CarDto> getByMinYearAndMaxYear(Year minYear, Year maxYear) {
    return carRepository.findByMinYearAndMaxYear(minYear, maxYear).stream()
        .map(carMapper::toDto)
        .toList();
  }

  @Override
  public List<CarDto> getByBrandAndMinYearAndMaxYear(String brand, Year minYear, Year maxYear) {
    List<CarDto> cars = getByBrand(brand);
    cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
    return cars;
  }

  @Override
  public List<CarDto> getByBrandAndModelAndMinYearAndMaxYear(
      String brand, String model, Year minYear, Year maxYear) {
    List<CarDto> cars = getByBrandAndModel(brand, model);
    cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
    return cars;
  }

  @Override
  public List<CarDto> getAll() {
    return carRepository.findAll().stream().map(carMapper::toDto).toList();
  }

  @Transactional
  @Override
  public CarDto update(Long id, CarUpdateDto carUpdateDto) {
    Car car = carMapper.toEntity(carUpdateDto);
    car.setId(id);
    carRepository.save(car);
    return carMapper.toDto(carUpdateDto);
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    carRepository.deleteById(id);
  }

  @Transactional
  @Override
  public void deleteByObjectId(String objectId) {
    carRepository.deleteByObjectId(objectId);
  }
}
