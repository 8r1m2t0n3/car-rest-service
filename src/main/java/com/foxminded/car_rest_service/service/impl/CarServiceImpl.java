package com.foxminded.car_rest_service.service.impl;

import com.foxminded.car_rest_service.exception.car.CarByObjectIdNotFoundException;
import com.foxminded.car_rest_service.model.dto.car.CarCreationDto;
import com.foxminded.car_rest_service.model.dto.car.CarDto;
import com.foxminded.car_rest_service.model.dto.car.CarSortingOptionsDto;
import com.foxminded.car_rest_service.model.dto.car.CarUpdateDto;
import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.repository.CarRepository;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;
import com.foxminded.car_rest_service.util.SequenceGenerator;
import com.foxminded.car_rest_service.util.mapper.CarMapper;
import com.foxminded.car_rest_service.util.mapper.CategoryMapper;
import com.foxminded.car_rest_service.util.specification.CarSpecification;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

  private final CategoryService categoryService;
  private final CarRepository carRepository;
  private final CategoryMapper categoryMapper;
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

    mergeCarCategories(car);

    carRepository.save(car);
    return carMapper.toDto(car);
  }

  private void mergeCarCategories(Car car) {
    car.getCategories()
        .forEach(
            category ->
                categoryService
                    .findByName(category.getName())
                    .ifPresentOrElse(
                        existingCategory -> category.setId(existingCategory.getId()),
                        () ->
                            category.setId(
                                categoryService
                                    .save(categoryMapper.toCreationDto(category))
                                    .getId())));
  }

  @Override
  public List<CarDto> getBySortOptions(CarSortingOptionsDto carSortingOptionsDto) {
    List<Car> cars = carRepository.findAll(CarSpecification.withSortOptions(carSortingOptionsDto));
    return cars.stream().map(carMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public CarDto getByObjectId(String objectId) {
    return carMapper.toDto(
        carRepository
            .findByObjectId(objectId)
            .orElseThrow(() -> new CarByObjectIdNotFoundException(objectId)));
  }

  @Override
  public List<CarDto> getAll() {
    return carRepository.findAll().stream().map(carMapper::toDto).collect(Collectors.toList());
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
