package com.foxminded.car_rest_service.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Custom annotation which used to aggregate any general mapstruct mapper configuration.
 *
 * @since componentModel = "spring" injectionStrategy = CONSTRUCTOR nullValueCheckStrategy =ALWAYS
 *     implementationPackage = "PACKAGE_NAME.impl
 */
@org.mapstruct.MapperConfig(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    implementationPackage = "<PACKAGE_NAME>.impl")
public class MapperConfig {}
