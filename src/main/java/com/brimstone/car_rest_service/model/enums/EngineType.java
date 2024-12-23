package com.brimstone.car_rest_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum EngineType {
  INLINE("Inline"),
  V_TYPE("V type"),
  FLAT("Flat"),
  BOXER("Boxer"),
  W_TYPE("W type"),
  ROTARY("Rotary"),
  ELECTRIC("Electric"),
  HYBRID("Hybrid"),
  DIESEL("Diesel"),
  TURBINE("Turbine"),
  HYDROGEN_FUEL_CELL("Hydrogen fuel cell");

  private final String label;

  public static EngineType fromString(String label) {
    for (EngineType type : values()) {
      if (type.label.equalsIgnoreCase(label)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No enum constant with label " + label);
  }
}
