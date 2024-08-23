package com.brimstone.car_rest_service.service.impl;

import com.brimstone.car_rest_service.exception.category.CategoryByIdNotFoundException;
import com.brimstone.car_rest_service.exception.category.CategoryByNameNotFoundException;
import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryUpdateDto;
import com.brimstone.car_rest_service.model.entity.Category;
import com.brimstone.car_rest_service.repository.CategoryRepository;
import com.brimstone.car_rest_service.service.CategoryService;
import com.brimstone.car_rest_service.util.mapper.CategoryMapper;
import java.util.List;
import java.util.Optional;
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
    Category category = categoryRepository.save(categoryMapper.toEntity(categoryCreationDto));
    return categoryMapper.toDto(category);
  }

  @Override
  public CategoryDto getById(Long id) {
    return categoryMapper.toDto(
        categoryRepository.findById(id).orElseThrow(() -> new CategoryByIdNotFoundException(id)));
  }

  @Override
  public CategoryDto getByName(String name) {
    return categoryMapper.toDto(
        categoryRepository
            .findByName(name)
            .orElseThrow(() -> new CategoryByNameNotFoundException(name)));
  }

  @Override
  public Optional<CategoryDto> findByName(String name) {
    Optional<Category> category = categoryRepository.findByName(name);
    Optional<CategoryDto> categoryDto = Optional.empty();
    if (category.isPresent()) {
      categoryDto = Optional.ofNullable(categoryMapper.toDto(category.get()));
    }
    return categoryDto;
  }

  @Override
  public List<CategoryDto> getAll() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public CategoryDto update(Long id, CategoryUpdateDto categoryUpdateDto) {
    Category category = categoryMapper.toEntity(categoryUpdateDto);
    category.setId(id);
    categoryRepository.save(category);
    return categoryMapper.toDto(category);
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }
}
