package com.brimstone.car_rest_service.service;

import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
  CategoryDto save(CategoryCreationDto categoryCreationDto);
  CategoryDto getById(UUID id);
  List<CategoryDto> getAll();
  void deleteById(UUID id);
}
