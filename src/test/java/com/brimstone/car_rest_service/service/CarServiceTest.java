package com.brimstone.car_rest_service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.brimstone.car_rest_service.exception.car.CarByObjectIdNotFoundException;
import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarSortingOptionsDto;
import com.brimstone.car_rest_service.model.entity.Car;
import com.brimstone.car_rest_service.repository.CarRepository;
import com.brimstone.car_rest_service.service.impl.CarServiceImpl;
import com.brimstone.car_rest_service.util.SequenceGenerator;
import com.brimstone.car_rest_service.util.mapper.CarMapper;
import com.brimstone.car_rest_service.util.mapper.CategoryMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

  @Mock CategoryService categoryService;
  @Mock CarRepository carRepository;
  @Mock CategoryMapper categoryMapper;
  @Mock CarMapper carMapper;

  @InjectMocks CarServiceImpl carService;

  @Value("${object-id.length}")
  private int objectIdLength;

  @Test
  void
      save_shouldSaveCarWithGeneratedObjectId_whenCarCreationDtoDoesNotContainObjectIdAndCarCreationDtoDoesNotContainCategories() {
    CarCreationDto carCreationDto = CarCreationDto.builder().categories(new ArrayList<>()).build();
    Car car = Car.builder().categories(new ArrayList<>()).build();

    when(carMapper.toEntity(carCreationDto)).thenReturn(car);

    String generatedObjectId = "generatedObjectId";

    try (MockedStatic<SequenceGenerator> generatorStatic =
        Mockito.mockStatic(SequenceGenerator.class)) {
      generatorStatic
          .when(() -> SequenceGenerator.generate(objectIdLength))
          .thenReturn(generatedObjectId);
    }

    when(categoryService.getAll()).thenReturn(new ArrayList<>());

    car.setObjectId(generatedObjectId);
    CarDto carDtoWithObjectId = CarDto.builder().objectId(generatedObjectId).build();

    when(carRepository.save(car)).thenReturn(car);
    when(carMapper.toDto(car)).thenReturn(carDtoWithObjectId);

    CarDto result = carService.save(carCreationDto);

    Assertions.assertEquals(result.getObjectId(), generatedObjectId);

    verify(carMapper).toEntity(carCreationDto);
    verify(carRepository).save(car);
    verify(carMapper).toDto(car);
  }

  @Test
  void
      save_shouldSaveCarWithObjectIdSpecifiedInCarCreationDto_whenCarCreationDtoContainsObjectIdAndCarCreationDtoDoesNotContainCategories() {
    CarCreationDto carCreationDto =
        CarCreationDto.builder().categories(new ArrayList<>()).objectId("nativeObjectId").build();
    Car car = Car.builder().categories(new ArrayList<>()).objectId("nativeObjectId").build();
    CarDto carDto =
        CarDto.builder().categories(new ArrayList<>()).objectId("nativeObjectId").build();

    when(carMapper.toEntity(carCreationDto)).thenReturn(car);
    when(categoryService.getAll()).thenReturn(new ArrayList<>());
    when(carRepository.save(car)).thenReturn(car);
    when(carMapper.toDto(car)).thenReturn(carDto);

    CarDto result = carService.save(carCreationDto);

    Assertions.assertEquals(result.getObjectId(), "nativeObjectId");

    verify(carMapper).toEntity(carCreationDto);
    try (MockedStatic<SequenceGenerator> generatorStatic =
        Mockito.mockStatic(SequenceGenerator.class)) {
      generatorStatic.verify(() -> SequenceGenerator.generate(objectIdLength), never());
    }
    verify(carRepository).save(car);
    verify(carMapper).toDto(car);
  }

  @Test
  void getBySortOptions_shouldReturnList_whenResponseContainsNonEmptyList() {
    CarSortingOptionsDto carSortingOptionsDto = CarSortingOptionsDto.builder().build();

    List<Car> sortedCarsList =
        List.of(
            Car.builder().id(1L).objectId("123").build(),
            Car.builder().id(2L).objectId("abc").build());

    when(carRepository.findAll(any(Specification.class))).thenReturn(sortedCarsList);

    List<CarDto> carDtoList =
        sortedCarsList.stream()
            .map(car -> CarDto.builder().id(car.getId()).objectId(car.getObjectId()).build())
            .collect(Collectors.toList());

    for (int i = 0; i < sortedCarsList.size(); i++) {
      when(carMapper.toDto(sortedCarsList.get(i))).thenReturn(carDtoList.get(i));
    }

    List<CarDto> result = carService.getBySortOptions(carSortingOptionsDto);

    Assertions.assertEquals(carDtoList, result);

    verify(carRepository).findAll(any(Specification.class));
    for (Car car : sortedCarsList) {
      verify(carMapper).toDto(car);
    }
  }

  @Test
  void getBySortOptions_shouldReturnEmptyList_whenDBResponseContainsEmptyList() {
    CarSortingOptionsDto carSortingOptionsDto = CarSortingOptionsDto.builder().build();
    List<Car> sortedCarsList = new ArrayList<>();

    when(carRepository.findAll(any(Specification.class))).thenReturn(sortedCarsList);

    List<CarDto> result = carService.getBySortOptions(carSortingOptionsDto);

    Assertions.assertTrue(result.isEmpty());

    verify(carRepository).findAll(any(Specification.class));
  }

  @Test
  void getByObjectId_shouldReturnCar_whenCarByObjectIdExists() {
    when(carRepository.findByObjectId("AAAA")).thenReturn(Optional.of(new Car()));

    carService.getByObjectId("AAAA");

    verify(carRepository).findByObjectId("AAAA");
  }

  @Test
  void getByObjectId_shouldThrowException_whenCarByObjectIdNotExists() {
    when(carRepository.findByObjectId("AAAA")).thenReturn(Optional.empty());

    Assertions.assertThrows(
        CarByObjectIdNotFoundException.class, () -> carService.getByObjectId("AAAA"));

    verify(carRepository).findByObjectId("AAAA");
  }

  @Test
  void getAll_shouldReturnList_whenDBResponseContainsNonEmptyList() {
    List<Car> cars =
        List.of(
            Car.builder().id(1L).objectId("123").build(),
            Car.builder().id(2L).objectId("abc").build());

    when(carRepository.findAll()).thenReturn(cars);

    List<CarDto> result = carService.getAll();

    Assertions.assertFalse(result.isEmpty());

    verify(carRepository).findAll();
  }

  @Test
  void getAll_shouldReturnEmptyList_whenDBResponseContainsEmptyList() {
    List<Car> cars = new ArrayList<>();

    when(carRepository.findAll()).thenReturn(cars);

    List<CarDto> result = carService.getAll();

    Assertions.assertTrue(result.isEmpty());

    verify(carRepository).findAll();
  }

  @Test
  void deleteByIdTest() {
    doNothing().when(carRepository).deleteById(1L);

    carService.deleteById(1L);

    verify(carRepository).deleteById(1L);
  }

  @Test
  void deleteByObjectIdTest() {
    doNothing().when(carRepository).deleteByObjectId("AAAA");

    carService.deleteByObjectId("AAAA");

    verify(carRepository).deleteByObjectId("AAAA");
  }
}
