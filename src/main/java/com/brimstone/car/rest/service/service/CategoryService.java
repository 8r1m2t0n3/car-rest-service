package com.brimstone.car.rest.service.service;

import com.brimstone.car.rest.service.model.dto.category.CategoryCreationDto;
import com.brimstone.car.rest.service.model.dto.category.CategoryDto;
import com.brimstone.car.rest.service.model.dto.category.CategoryUpdateDto;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

  CategoryDto save(CategoryCreationDto categoryCreationDto);

  CategoryDto getById(Long id);

  CategoryDto getByName(String name);

  Optional<CategoryDto> findByName(String name);

  List<CategoryDto> getAll();

  CategoryDto update(Long id, CategoryUpdateDto categoryUpdateDto);

  void deleteById(Long id);
}
