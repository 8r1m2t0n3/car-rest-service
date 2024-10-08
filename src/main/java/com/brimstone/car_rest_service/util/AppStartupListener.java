package com.brimstone.car_rest_service.util;

import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.service.CarService;
import com.brimstone.car_rest_service.service.CategoryService;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupListener implements ApplicationListener<ContextRefreshedEvent> {

  private final CarService carService;
  private final CategoryService categoryService;

  private static final String COMMA_DELIMITER = ",";
  private static final int OBJECT_ID_COLUMN_INDEX = 0;
  private static final int BRAND_COLUMN_INDEX = 1;
  private static final int YEAR_COLUMN_INDEX = 2;
  private static final int MODEL_COLUMN_INDEX = 3;
  private static final int CATEGORIES_COLUMN_INDEX = 4;

  @Value("${path.static.file.cars}")
  private String carsBackupFilePath;

  public void readCsvFile() throws IOException {
    if (carService.getAll().isEmpty() || categoryService.getAll().isEmpty()) {
      try (BufferedReader br = Files.newBufferedReader(Paths.get(carsBackupFilePath))) {
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
          String[] values = line.split(COMMA_DELIMITER);

          CarCreationDto carCreationDto =
              CarCreationDto.builder()
                  .objectId(values[OBJECT_ID_COLUMN_INDEX])
                  .brand(values[BRAND_COLUMN_INDEX].toLowerCase())
                  .year(Year.parse(values[YEAR_COLUMN_INDEX]))
                  .model(values[MODEL_COLUMN_INDEX].toLowerCase())
                  .categories(new ArrayList<>())
                  .build();

          for (int i = CATEGORIES_COLUMN_INDEX; i < values.length; i++) {
            carCreationDto
                .getCategories()
                .add(
                    new CategoryCreationDto(
                        values[i].replaceAll("[" + '"' + " ]", "").toLowerCase()));
          }
          carService.save(carCreationDto);
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
