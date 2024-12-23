package com.brimstone.car_rest_service.util.enum_converter;

import com.brimstone.car_rest_service.model.enums.DriveType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DriveTypeConverter implements AttributeConverter<DriveType, String> {

  @Override
  public String convertToDatabaseColumn(DriveType attribute) {
    return attribute != null ? attribute.getLabel() : null;
  }

  @Override
  public DriveType convertToEntityAttribute(String dbData) {
    return dbData != null ? DriveType.fromString(dbData) : null;
  }
}
