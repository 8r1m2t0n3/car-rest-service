package com.brimstone.car_rest_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum DriveType {
  FWD("FWD"),
  RWD("RWD"),
  FOUR_WD("4WD"),
  AWD("AWD");

  private final String label;

  public static DriveType fromString(String label) {
    for (DriveType type : values()) {
      if (type.label.equalsIgnoreCase(label)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No enum constant with label " + label);
  }
}
