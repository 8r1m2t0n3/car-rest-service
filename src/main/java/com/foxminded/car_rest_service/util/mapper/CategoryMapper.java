package com.foxminded.car_rest_service.util.mapper;

import com.foxminded.car_rest_service.config.MapperConfig;
import com.foxminded.car_rest_service.model.dto.category.CategoryCreationDto;
import com.foxminded.car_rest_service.model.dto.category.CategoryDto;
import com.foxminded.car_rest_service.model.dto.category.CategoryUpdateDto;
import com.foxminded.car_rest_service.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
  Category toEntity(CategoryDto categoryDto);
  Category toEntity(CategoryCreationDto categoryCreationDto);
  Category toEntity(CategoryUpdateDto categoryUpdateDto);
  CategoryDto toDto(Category category);
  CategoryDto toDto(CategoryCreationDto categoryCreationDto);
  CategoryDto toDto(CategoryUpdateDto categoryUpdateDto);
}
