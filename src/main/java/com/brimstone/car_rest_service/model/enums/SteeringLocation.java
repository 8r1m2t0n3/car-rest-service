package com.brimstone.car_rest_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum SteeringLocation {
  LEFT("Left"),
  RIGHT("Right");

  private final String label;

  public static SteeringLocation fromString(String label) {
    for (SteeringLocation type : values()) {
      if (type.label.equalsIgnoreCase(label)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No enum constant with label " + label);
  }
}
