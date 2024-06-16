package com.foxminded.car_rest_service.service;

import com.foxminded.car_rest_service.model.dto.CarCreationDto;
import com.foxminded.car_rest_service.model.entity.Category;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.repository.CarRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CarService {

  private final CarRepository carRepository;
  private final CategoryService categoryService;

  @Transactional
  public Car save(CarCreationDto carCreationDto) {
    Car car = Car.builder()
        .objectId(UUID.randomUUID().toString())
        .brand(carCreationDto.getBrand())
        .model(carCreationDto.getModel())
        .year(carCreationDto.getYear())
        .build();

    for (Category category : carCreationDto.getCategories()) {
      Optional<Category> categoryOptional = categoryService.getByName(category.getName());
      if (categoryOptional.isEmpty()) {
        categoryService.save(category);
        car.getCategories().add(category);
      } else {
        car.getCategories().add(categoryOptional.get());
      }
    }

    carRepository.save(car);
    log.info("Saved car with objectId: {}", car.getObjectId());
    return car;
  }

  public Optional<Car> getById(Long id) {
    return carRepository.findById(id);
  }

  public Optional<Car> getByObjectId(String objectId) {
    return carRepository.findByObjectId(objectId);
  }

  public List<Car> getByBrand(String brand) {
    return carRepository.findByBrand(brand);
  }

  public List<Car> getByBrandAndModel(String brand, String model) {
    return carRepository.findByBrandAndModel(brand, model);
  }

  public List<Car> getByMinYearAndMaxYear(Year minYear, Year maxYear) {
    return carRepository.findByMinYearAndMaxYear(minYear, maxYear);
  }

  public List<Car> getAll() {
    return carRepository.findAll();
  }

  @Transactional
  public Car update(Car car, Long id) {
    car.setId(id);
    carRepository.save(car);
    log.info("Updated car with id: {}", car.getId());
    return car;
  }

  @Transactional
  public void deleteById(Long id) {
    carRepository.deleteById(id);
    log.info("Deleted car with id: {}", id);
  }

  @Transactional
  public void deleteByObjectId(UUID objectId) {
    carRepository.deleteByObjectId(objectId.toString());
    log.info("Deleted car with objectId: {}", objectId);
  }
}
