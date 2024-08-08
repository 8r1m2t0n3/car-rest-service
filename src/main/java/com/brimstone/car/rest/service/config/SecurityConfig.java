package com.brimstone.car.rest.service.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.brimstone.car.rest.service.security.JwtAuthConverter;
import com.brimstone.car.rest.service.security.JwtAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class SecurityConfig {

  private final JwtAuthConverter jwtAuthConverter;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private static final String[] WHITELIST = {"/api/v1/keycloak/**", "/swagger-ui/**"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers(WHITELIST)
                    .permitAll()
                    .requestMatchers(HttpMethod.GET)
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .cors(Customizer.withDefaults())
        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
    http.oauth2ResourceServer(
        server ->
            server
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                .authenticationEntryPoint(jwtAuthenticationEntryPoint));
    http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

    return http.build();
  }
}
