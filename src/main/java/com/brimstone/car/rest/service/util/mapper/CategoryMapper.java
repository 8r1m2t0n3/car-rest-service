package com.brimstone.car.rest.service.util.mapper;

import com.brimstone.car.rest.service.config.MapperConfig;
import com.brimstone.car.rest.service.model.dto.category.CategoryCreationDto;
import com.brimstone.car.rest.service.model.dto.category.CategoryDto;
import com.brimstone.car.rest.service.model.dto.category.CategoryUpdateDto;
import com.brimstone.car.rest.service.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

  Category toEntity(CategoryDto categoryDto);

  Category toEntity(CategoryCreationDto categoryCreationDto);

  Category toEntity(CategoryUpdateDto categoryUpdateDto);

  CategoryDto toDto(Category category);

  CategoryDto toDto(CategoryCreationDto categoryCreationDto);

  CategoryDto toDto(CategoryUpdateDto categoryUpdateDto);

  CategoryCreationDto toCreationDto(Category category);
}
