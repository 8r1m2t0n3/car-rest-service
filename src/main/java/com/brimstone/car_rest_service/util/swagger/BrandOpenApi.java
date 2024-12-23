package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.brand.BrandCreationDto;
import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;

@Tag(name = "Brands", description = "RESTful API for managing brands of cars")
public interface BrandOpenApi {

  @Operation(summary = "Creating new brand", security = @SecurityRequirement(name = "bearerAuth"))
  BrandDto createBrand(BrandCreationDto brandCreateDto);

  @Operation(summary = "Deletes brand by id", security = @SecurityRequirement(name = "bearerAuth"))
  void deleteBrandById(UUID id);

  @Operation(summary = "Retrieves brand by id")
  BrandDto getBrandById(UUID id);

  @Operation(summary = "Retrieves all brands")
  List<BrandDto> getAllBrands();
}
