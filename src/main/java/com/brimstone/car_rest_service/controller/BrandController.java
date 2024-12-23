package com.brimstone.car_rest_service.controller;

import com.brimstone.car_rest_service.model.dto.brand.BrandCreationDto;
import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import com.brimstone.car_rest_service.service.BrandService;
import com.brimstone.car_rest_service.util.swagger.BrandOpenApi;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController implements BrandOpenApi {

  private final BrandService brandService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BrandDto createBrand(@RequestBody @Valid BrandCreationDto brandCreateDto) {
    return brandService.save(brandCreateDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBrandById(@PathVariable UUID id) {
    brandService.deleteById(id);
  }

  @GetMapping("/{id}")
  public BrandDto getBrandById(@PathVariable UUID id) {
    return brandService.getById(id);
  }

  @GetMapping
  public List<BrandDto> getAllBrands() {
    return brandService.getAll();
  }
}
