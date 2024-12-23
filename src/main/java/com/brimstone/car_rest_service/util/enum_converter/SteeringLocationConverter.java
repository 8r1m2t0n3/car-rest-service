package com.brimstone.car_rest_service.util.enum_converter;

import com.brimstone.car_rest_service.model.enums.SteeringLocation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SteeringLocationConverter implements AttributeConverter<SteeringLocation, String> {

  @Override
  public String convertToDatabaseColumn(SteeringLocation attribute) {
    return attribute != null ? attribute.getLabel() : null;
  }

  @Override
  public SteeringLocation convertToEntityAttribute(String dbData) {
    return dbData != null ? SteeringLocation.fromString(dbData) : null;
  }
}
