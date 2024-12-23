package com.brimstone.car_rest_service.service.impl;

import com.brimstone.car_rest_service.exception.car.CarByIdNotFoundException;
import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarUpdateDto;
import com.brimstone.car_rest_service.model.entity.Car;
import com.brimstone.car_rest_service.repository.CarRepository;
import com.brimstone.car_rest_service.service.CarService;
import com.brimstone.car_rest_service.util.mapper.CarMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final CarMapper carMapper;

  @Transactional
  @Override
  public CarDto save(CarCreationDto carCreationDto) {
    return carMapper.toDto(carRepository.save(carMapper.toEntity(carCreationDto)));
  }

  @Override
  public List<CarDto> getAll() {
    return carRepository.findAll().stream()
        .map(carMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public CarDto getById(UUID id) {
    return carRepository.findById(id)
        .map(carMapper::toDto)
        .orElseThrow(() -> new CarByIdNotFoundException(id));
  }

  @Transactional
  @Override
  public CarDto update(UUID id, CarUpdateDto carUpdateDto) {
    Car car = carMapper.toEntity(carUpdateDto);
    car.setId(id);
    carRepository.save(car);
    return carMapper.toDto(carUpdateDto);
  }

  @Transactional
  @Override
  public void deleteById(UUID id) {
    carRepository.deleteById(id);
  }
}
