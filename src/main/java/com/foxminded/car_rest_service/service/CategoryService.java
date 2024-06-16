package com.foxminded.car_rest_service.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.foxminded.car_rest_service.model.entity.Category;
import com.foxminded.car_rest_service.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public Category save(Category category) {
    categoryRepository.save(category);
    log.info("Saved category with name: {}", category.getName());
    return category;
  }

  public Optional<Category> getById(Long categoryId) {
    return categoryRepository.findById(categoryId);
  }

  public Optional<Category> getByName(String categoryName) {
    return categoryRepository.findByName(categoryName);
  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  @Transactional
  public Category update(Category category, Long categoryId) {
    category.setId(categoryId);
    categoryRepository.save(category);
    log.info("Updated category with id: {}", category.getId());
    return category;
  }

  @Transactional
  public void deleteById(Long categoryId) {
    categoryRepository.deleteById(categoryId);
    log.info("Deleted category with id: {}", categoryId);
  }
}
