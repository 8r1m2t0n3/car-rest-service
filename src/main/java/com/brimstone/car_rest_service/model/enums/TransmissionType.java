package com.brimstone.car_rest_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TransmissionType {
  MANUAL("Manual"),
  AUTOMATIC("Automatic");

  private final String label;

  public static TransmissionType fromString(String label) {
    for (TransmissionType type : values()) {
      if (type.label.equalsIgnoreCase(label)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No enum constant with label " + label);
  }
}
