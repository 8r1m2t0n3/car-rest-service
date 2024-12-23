package com.brimstone.car_rest_service.util.enum_converter;

import com.brimstone.car_rest_service.model.enums.DriveType;
import com.brimstone.car_rest_service.model.enums.EngineType;
import jakarta.persistence.AttributeConverter;

public class EngineTypeConverter implements AttributeConverter<EngineType, String> {

  @Override
  public String convertToDatabaseColumn(EngineType attribute) {
    return attribute != null ? attribute.getLabel() : null;
  }

  @Override
  public EngineType convertToEntityAttribute(String dbData) {
    return dbData != null ? EngineType.fromString(dbData) : null;
  }
}
