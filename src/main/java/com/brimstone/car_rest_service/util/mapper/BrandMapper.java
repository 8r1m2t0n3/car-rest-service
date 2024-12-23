package com.brimstone.car_rest_service.util.mapper;

import com.brimstone.car_rest_service.config.MapperConfig;
import com.brimstone.car_rest_service.model.dto.brand.BrandCreationDto;
import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import com.brimstone.car_rest_service.model.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BrandMapper {
  BrandDto toDto(Brand brand);
  Brand toEntity(BrandDto brandDto);
  Brand toEntity(BrandCreationDto brandCreateDto);
}
