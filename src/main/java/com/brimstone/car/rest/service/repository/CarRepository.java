package com.brimstone.car.rest.service.repository;

import com.brimstone.car.rest.service.model.entity.Car;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

	Optional<Car> findByObjectId(String objectId);

	void deleteByObjectId(String objectId);
}
