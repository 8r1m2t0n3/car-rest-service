package com.brimstone.car_rest_service.service.impl;

import com.brimstone.car_rest_service.exception.category.CategoryByIdNotFoundException;
import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import com.brimstone.car_rest_service.repository.CategoryRepository;
import com.brimstone.car_rest_service.service.CategoryService;
import com.brimstone.car_rest_service.util.mapper.CategoryMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Transactional
  @Override
  public CategoryDto save(CategoryCreationDto categoryCreationDto) {
    return categoryMapper.toDto(
        categoryRepository.save(
            categoryMapper.toEntity(categoryCreationDto)));
  }

  @Transactional
  @Override
  public void deleteById(UUID id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public CategoryDto getById(UUID id) {
    return categoryMapper.toDto(
        categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryByIdNotFoundException(id)));
  }

  @Override
  public List<CategoryDto> getAll() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toDto)
        .collect(Collectors.toList());
  }
}
