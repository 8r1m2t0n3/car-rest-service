package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.category.CategoryCreationDto;
import com.brimstone.car_rest_service.model.dto.category.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;

@Tag(name = "Categories", description = "RESTful api for managing car's categories")
public interface CategoryOpenApi {

  @Operation(summary = "Creates new category", security = @SecurityRequirement(name = "bearerAuth"))
  CategoryDto saveCategory(CategoryCreationDto categoryCreationDto);

  @Operation(summary = "Deletes category by id", security = @SecurityRequirement(name = "bearerAuth"))
  void deleteCategoryById(UUID id);

  @Operation(summary = "Retrieves category by id")
  CategoryDto getCategoryById(UUID id);

  @Operation(summary = "Retrieves all categories")
  List<CategoryDto> getAllCategories();
}
