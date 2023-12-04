package com.foxminded.car_rest_service.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foxminded.car_rest_service.model.Car;
import com.foxminded.car_rest_service.repository.CarRepository;

import jakarta.transaction.Transactional;

@Service
public class CarService {

	private CarRepository carRepository;

	private Logger logger = LoggerFactory.getLogger(CarService.class);
	private Random random = new Random();

	private static final String OBJECT_ID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Transactional
	public Car save(Car car) {
		carRepository.save(car);
		logger.info("Saved car with objectId: {}", car.getObjectId());
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
		logger.info("Updated car with id: {}", car.getId());
		return car;
	}

	@Transactional
	public void deleteById(Long id) {
		carRepository.deleteById(id);
		logger.info("Deleted car with id: {}", id);
	}

	@Transactional
	public void deleteByObjectId(String objectId) {
		carRepository.deleteByObjectId(objectId);
		logger.info("Deleted car with objectId: {}", objectId);
	}

	public String generateUniqueObjectId() {
		StringBuilder objectId = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			objectId.append(OBJECT_ID_CHARACTERS.charAt(random.nextInt(OBJECT_ID_CHARACTERS.length())));
		}
		logger.info("Generated objectId: {}", objectId);

		if (carRepository.findByObjectId(objectId.toString()).isPresent()) {
			logger.warn("Generated objectId: {} is not unique, regeneration required", objectId);
			return null;
		}
		logger.info("Uniqueness of objectId: {} is confirmed", objectId);

		return objectId.toString();
	}
}
