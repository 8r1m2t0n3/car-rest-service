//package com.brimstone.car.rest.service.service;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.brimstone.car.rest.service.model.entity.Car;
//import com.brimstone.car.rest.service.repository.CarRepository;
//import com.brimstone.car.rest.service.util.mapper.CarMapper;
//import com.brimstone.car.rest.service.util.mapper.CategoryMapper;
//import java.time.Year;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class CarServiceTest {
//
//  @Mock CategoryService categoryService;
//  @Mock CarRepository carRepository;
//  @Mock CategoryMapper categoryMapper;
//  @Mock CarMapper carMapper;
//
//  @InjectMocks CarService carService;
//
//  @Test
//  void saveTest() {
//    Car car = new Car();
//    when(carRepository.save(car)).thenReturn(car);
//
//    carService.save(car);
//
//    verify(carRepository).save(car);
//  }
//
//  @Test
//  void getByIdTest() {
//    when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
//
//    carService.getById(1L);
//
//    verify(carRepository).findById(1L);
//  }
//
//  @Test
//  void getByObjectIdTest() {
//    when(carRepository.findByObjectId("AAAA")).thenReturn(Optional.of(new Car()));
//
//    carService.getByObjectId("AAAA");
//
//    verify(carRepository).findByObjectId("AAAA");
//  }
//
//  @Test
//  void getByBrandTest() {
//    when(carRepository.findByBrand("brand")).thenReturn(List.of(new Car()));
//
//    carService.getByBrand("brand");
//
//    verify(carRepository).findByBrand("brand");
//  }
//
//  @Test
//  void getByMinYearAndMaxYearTest() {
//    when(carRepository.findByMinYearAndMaxYear(Year.of(2020), Year.of(2023)))
//        .thenReturn(List.of(new Car()));
//
//    carService.getByMinYearAndMaxYear(Year.of(2020), Year.of(2023));
//
//    verify(carRepository).findByMinYearAndMaxYear(Year.of(2020), Year.of(2023));
//  }
//
//  @Test
//  void getAllTest() {
//    when(carRepository.findAll()).thenReturn(new ArrayList<>());
//
//    carService.getAll();
//
//    verify(carRepository).findAll();
//  }
//
//  @Test
//  void updateTest() {
//    Car car = new Car();
//    when(carRepository.save(car)).thenReturn(car);
//
//    carService.update(car, 1L);
//
//    verify(carRepository).save(car);
//  }
//
//  @Test
//  void deleteByIdTest() {
//    doNothing().when(carRepository).deleteById(1L);
//
//    carService.deleteById(1L);
//
//    verify(carRepository).deleteById(1L);
//  }
//
//  @Test
//  void deleteByObjectIdTest() {
//    doNothing().when(carRepository).deleteByObjectId("AAAA");
//
//    carService.deleteByObjectId("AAAA");
//
//    verify(carRepository).deleteByObjectId("AAAA");
//  }
//
//  @Test
//  void generateUniqueObjectId_shouldNotReturnNull_whenGeneratedObjectIdIsUnique() {
//    when(carRepository.findByObjectId(anyString())).thenReturn(Optional.empty());
//
//    assertFalse(carService.generateUniqueObjectId().isEmpty());
//
//    verify(carRepository).findByObjectId(anyString());
//  }
//
//  @Test
//  void generateUniqueObjectId_shouldReturnNull_whenGeneratedObjectIdIsNotUnique() {
//    when(carRepository.findByObjectId(anyString())).thenReturn(Optional.of(new Car()));
//
//    assertNull(carService.generateUniqueObjectId());
//
//    verify(carRepository).findByObjectId(anyString());
//  }
//}
