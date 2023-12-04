package com.foxminded.car_rest_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.foxminded.car_rest_service.model.Car;
import com.foxminded.car_rest_service.model.Category;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;

@Component
public class AppStartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private CarService carService;
	private CategoryService categoryService;

	private static final String COMMA_DELIMITER = ",";
	private static final int OBJECT_ID_COLUMN_INDEX = 0;
	private static final int BRAND_COLUMN_INDEX = 1;
	private static final int YEAR_COLUMN_INDEX = 2;
	private static final int MODEL_COLUMN_INDEX = 3;
	private static final int CATEGORIES_COLUMN_INDEX = 4;

	public AppStartupListener(CarService carService, CategoryService categoryService) {
		this.carService = carService;
		this.categoryService = categoryService;
	}

	private void addCategoryToCar(Car car, String categoryName) {
		Optional<Category> categoryOptional = categoryService.getByName(categoryName);
		if (categoryOptional.isEmpty()) {
			Category category = new Category(categoryName);
			categoryService.save(category);
			car.addCategory(category);
		} else {
			car.addCategory(categoryOptional.get());
		}
	}

	public void readCsvFile() throws IOException {
		if (carService.getAll().isEmpty() && categoryService.getAll().isEmpty()) {
			try (BufferedReader br = Files.newBufferedReader(Paths.get("/cars.csv"))) {
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					String[] values = line.split(COMMA_DELIMITER);
					Car car = new Car();
					car.setObjectId(values[OBJECT_ID_COLUMN_INDEX]);
					car.setBrand(values[BRAND_COLUMN_INDEX].toLowerCase());
					car.setYear(Year.parse(values[YEAR_COLUMN_INDEX]));
					car.setModel(values[MODEL_COLUMN_INDEX].toLowerCase());
					for (int i = CATEGORIES_COLUMN_INDEX; i < values.length; i++) {
						String categoryName = values[i].replaceAll("[" + '"' + " ]", "").toLowerCase();
						addCategoryToCar(car, categoryName);
					}
					carService.save(car);
				}
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			readCsvFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
