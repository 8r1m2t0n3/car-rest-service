package com.foxminded.car_rest_service.repository;

import com.foxminded.car_rest_service.model.entity.Car;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByBrand(String brand);

	List<Car> findByBrandAndModel(String brand, String model);

	Optional<Car> findByObjectId(String objectId);

	void deleteByObjectId(String objectId);

	@Query("from Car c where c.year >= ?1 and c.year <= ?2")
	List<Car> findByMinYearAndMaxYear(Year minYear, Year maxYear);
}
