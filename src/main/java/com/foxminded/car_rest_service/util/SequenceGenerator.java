package com.foxminded.car_rest_service.util;

import java.util.Random;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SequenceGenerator {

  private static final String OBJECT_ID_CHARACTERS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public static String generate(long length) {
    return new Random()
        .ints(length, 0, OBJECT_ID_CHARACTERS.length())
        .mapToObj(i -> String.valueOf(OBJECT_ID_CHARACTERS.charAt(i)))
        .collect(Collectors.joining());
  }
}
