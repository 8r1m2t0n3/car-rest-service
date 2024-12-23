package com.brimstone.car_rest_service.controller;

import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import com.brimstone.car_rest_service.service.CategoryService;
import com.brimstone.car_rest_service.util.swagger.CategoryOpenApi;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController implements CategoryOpenApi {

  private final CategoryService categoryService;

  @PostMapping
  public CategoryDto saveCategory(@RequestParam @Valid CategoryCreationDto categoryCreationDto) {
    return categoryService.save(categoryCreationDto);
  }

  @DeleteMapping("/{id}")
  public void deleteCategoryById(@PathVariable UUID id) {
    categoryService.deleteById(id);
  }

  @GetMapping("/{id}")
  public CategoryDto getCategoryById(@PathVariable UUID id) {
    return categoryService.getById(id);
  }

  @GetMapping
  public List<CategoryDto> getAllCategories() {
    return categoryService.getAll();
  }
}
