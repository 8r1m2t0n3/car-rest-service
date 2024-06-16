package com.foxminded.car_rest_service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.model.entity.Category;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;

@Component
@AllArgsConstructor
public class AppStartupListener implements ApplicationListener<ContextRefreshedEvent> {

  private final CarService carService;
  private final CategoryService categoryService;

  private static final String COMMA_DELIMITER = ",";
  private static final int OBJECT_ID_COLUMN_INDEX = 0;
  private static final int BRAND_COLUMN_INDEX = 1;
  private static final int YEAR_COLUMN_INDEX = 2;
  private static final int MODEL_COLUMN_INDEX = 3;
  private static final int CATEGORIES_COLUMN_INDEX = 4;

  private void addCategoryToCar(Car car, String categoryName) {
    Optional<Category> category = categoryService.getByName(categoryName);
    if (category.isEmpty()) {
      Category newCategory = Category.builder().name(categoryName).build();
      categoryService.save(newCategory);
      car.getCategories().add(newCategory);
    } else {
      car.getCategories().add(category.get());
    }
  }

  public void readCsvFile() throws IOException {
    if (carService.getAll().isEmpty() && categoryService.getAll().isEmpty()) {
      try (BufferedReader br = Files.newBufferedReader(Paths.get("/cars.csv"))) {
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
          String[] values = line.split(COMMA_DELIMITER);

          Car car = Car.builder()
              .objectId(values[OBJECT_ID_COLUMN_INDEX])
              .brand(values[BRAND_COLUMN_INDEX].toLowerCase())
              .year(Year.parse(values[YEAR_COLUMN_INDEX]))
              .model(values[MODEL_COLUMN_INDEX].toLowerCase())
              .build();

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
      throw new RuntimeException(e);
    }
  }
}
