package com.foxminded.car_rest_service.model.pojo;

import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class YearRange {
  private Year start;
  private Year end;
}
