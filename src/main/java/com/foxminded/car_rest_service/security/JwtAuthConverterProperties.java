package com.foxminded.car_rest_service.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
@Builder
@Getter
@Setter
public class JwtAuthConverterProperties {
	private String resourceId;
	private String principalAttribute;
}
