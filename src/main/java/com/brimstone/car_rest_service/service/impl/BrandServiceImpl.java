package com.brimstone.car_rest_service.service.impl;

import com.brimstone.car_rest_service.exception.brand.BrandByIdNotFoundException;
import com.brimstone.car_rest_service.exception.brand.BrandAlreadyExistsByNameException;
import com.brimstone.car_rest_service.model.dto.brand.BrandCreationDto;
import com.brimstone.car_rest_service.model.dto.brand.BrandDto;
import com.brimstone.car_rest_service.repository.BrandRepository;
import com.brimstone.car_rest_service.service.BrandService;
import com.brimstone.car_rest_service.util.mapper.BrandMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final BrandMapper brandMapper;

  @Transactional
  @Override
  public BrandDto save(BrandCreationDto brandCreateDto) {
    brandRepository.findByName(brandCreateDto.getName())
        .ifPresent(existing -> {
          throw new BrandAlreadyExistsByNameException(brandCreateDto.getName());
        });
    return brandMapper.toDto(brandRepository.save(brandMapper.toEntity(brandCreateDto))
    );
  }

  @Transactional
  @Override
  public void deleteById(UUID id) {
    brandRepository.deleteById(id);
  }

  @Override
  public BrandDto getById(UUID id) {
    return brandRepository.findById(id)
        .map(brandMapper::toDto)
        .orElseThrow(() -> new BrandByIdNotFoundException(id));
  }

  @Override
  public List<BrandDto> getAll() {
    return brandRepository.findAll().stream()
        .map(brandMapper::toDto)
        .toList();
  }
}
