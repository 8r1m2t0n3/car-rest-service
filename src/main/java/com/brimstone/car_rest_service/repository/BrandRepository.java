package com.brimstone.car_rest_service.repository;

import com.brimstone.car_rest_service.model.entity.Brand;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, UUID> {

  Optional<Brand> findByName(String name);
}
