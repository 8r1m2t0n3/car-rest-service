package com.brimstone.car_rest_service.util.mapper;

import com.brimstone.car_rest_service.config.MapperConfig;
import com.brimstone.car_rest_service.model.dto.car.CarCreationDto;
import com.brimstone.car_rest_service.model.dto.car.CarDto;
import com.brimstone.car_rest_service.model.dto.car.CarUpdateDto;
import com.brimstone.car_rest_service.model.entity.Car;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CarMapper {

   Car toEntity(CarDto carDto);

  Car toEntity(CarCreationDto carCreationDto);

  Car toEntity(CarUpdateDto carUpdateDto);

  CarDto toDto(Car car);

  CarDto toDto(CarCreationDto carCreationDto);

  CarDto toDto(CarUpdateDto carUpdateDto);
}
