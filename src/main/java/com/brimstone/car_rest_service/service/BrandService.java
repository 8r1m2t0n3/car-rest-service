package com.brimstone.car_rest_service.service;

import com.brimstone.car_rest_service.model.dto.brand.BrandCreationDto;
import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import java.util.List;
import java.util.UUID;

public interface BrandService {
  BrandDto save(BrandCreationDto brandCreateDto);
  void deleteById(UUID id);
  BrandDto getById(UUID id);
  List<BrandDto> getAll();
}
