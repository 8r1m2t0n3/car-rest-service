package com.foxminded.car_rest_service.controller;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.car_rest_service.model.Car;
import com.foxminded.car_rest_service.model.Category;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars", description = "Methods for working with cars")
class CarController {

	private CarService carService;
	private CategoryService categoryService;

	public CarController(CarService carService, CategoryService categoryService) {
		this.carService = carService;
		this.categoryService = categoryService;
	}

	@GetMapping
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get all cars")
	List<Car> getAll() {
		return carService.getAll();
	}

	@GetMapping("object-id/{objectId}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get car by objectId")
	Optional<Car> getCarByObjectId(@Parameter(description = "Unique object id") @PathVariable String objectId) {
		return carService.getByObjectId(objectId);
	}

	@GetMapping("/brand/{brand}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get cars by brand")
	List<Car> getCarsByBrand(@Parameter(description = "Brand name") @PathVariable String brand) {
		return carService.getByBrand(brand);
	}

	@GetMapping("/brand/{brand}/model/{model}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get cars by model")
	List<Car> getByBrandAndModel(@Parameter(description = "Brand name") @PathVariable String brand,
			@Parameter(description = "Model name") @PathVariable String model) {
		List<Car> cars = carService.getByBrand(brand);
		cars.removeIf(o -> !o.getModel().equals(model));
		return cars;
	}

	@GetMapping("/min-year/{minYear}/max-year/{maxYear}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get cars by min and max years of manufacture")
	List<Car> getByMinYearAndMaxYear(@Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
			@Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
		return carService.getByMinYearAndMaxYear(minYear, maxYear);
	}

	@GetMapping("/brand/{brand}/min-year/{minYear}/max-year/{maxYear}")
	@Operation(summary = "Get cars by brand and min and max years of manufacture")
	List<Car> getByBrandAndMinYearAndMaxYear(@Parameter(description = "Brand name") @PathVariable String brand,
			@Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
			@Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
		List<Car> cars = carService.getByBrand(brand);
		cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
		return cars;
	}

	@GetMapping("/brand/{brand}/model/{model}/min-year/{minYear}/max-year/{maxYear}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "Get cars by brand, model and min and max years of manufacture")
	List<Car> getByBrandAndModelAndMinYearAndMaxYear(@Parameter(description = "Brand name") @PathVariable String brand,
			@Parameter(description = "Model name") @PathVariable String model,
			@Parameter(description = "Min year of manufacture") @PathVariable Year minYear,
			@Parameter(description = "Max year of manufacture") @PathVariable Year maxYear) {
		List<Car> cars = carService.getByBrand(brand);
		cars.removeIf(o -> !o.getModel().equals(model));
		cars.removeIf(o -> (o.getYear().isBefore(minYear) || o.getYear().isAfter(maxYear)));
		return cars;
	}

	@PostMapping
	@PreAuthorize("isAuthenticated()")
	@Operation(summary = "Add car with specified brand, model, year of manufacture and categories", security = @SecurityRequirement(name = "bearerAuth"))
	Car add(@Parameter(description = "Brand name") @RequestParam String brand,
			@Parameter(description = "Model name") @RequestParam String model,
			@Parameter(description = "Year of manufacture") @RequestParam Year year,
			@Parameter(description = "Categories names") @RequestParam(name = "categories") String[] categoriesNames) {
		Car car = new Car();

		String uniqueObjectId = null;
		while (uniqueObjectId == null) {
			uniqueObjectId = carService.generateUniqueObjectId();
		}

		car.setObjectId(uniqueObjectId);
		car.setBrand(brand);
		car.setModel(model);
		car.setYear(year);

		for (String categoryName : categoriesNames) {
			Optional<Category> categoryOptional = categoryService.getByName(categoryName);
			if (categoryOptional.isEmpty()) {
				Category category = new Category(categoryName);
				categoryService.save(category);
				car.addCategory(category);
			} else {
				car.addCategory(categoryOptional.get());
			}
		}

		return carService.save(car);
	}

	@DeleteMapping
	@PreAuthorize("isAuthenticated()")
	@Operation(summary = "Delete car by objectId", security = @SecurityRequirement(name = "bearerAuth"))
	void delete(@Parameter(description = "Unique object id") @RequestParam("object-id") String objectId) {
		carService.deleteByObjectId(objectId);
	}
}
