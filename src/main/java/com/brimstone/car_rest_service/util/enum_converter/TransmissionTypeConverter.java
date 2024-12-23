package com.brimstone.car_rest_service.util.enum_converter;

import com.brimstone.car_rest_service.model.enums.TransmissionType;
import jakarta.persistence.AttributeConverter;

public class TransmissionTypeConverter implements AttributeConverter<TransmissionType, String> {

  @Override
  public String convertToDatabaseColumn(TransmissionType attribute) {
    return attribute != null ? attribute.getLabel() : null;
  }

  @Override
  public TransmissionType convertToEntityAttribute(String dbData) {
    return dbData != null ? TransmissionType.fromString(dbData) : null;
  }
}
