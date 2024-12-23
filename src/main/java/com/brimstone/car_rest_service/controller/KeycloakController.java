package com.brimstone.car_rest_service.controller;

import com.brimstone.car_rest_service.model.dto.user.KeycloakUserCredentialsDto;
import com.brimstone.car_rest_service.service.KeycloakService;
import com.brimstone.car_rest_service.util.swagger.KeycloakOpenApi;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keycloak")
@AllArgsConstructor
public class KeycloakController implements KeycloakOpenApi {

  private final KeycloakService keycloakService;

  @PostMapping("/authenticate")
  public String getAccessToken(@RequestBody @Valid KeycloakUserCredentialsDto keycloakUserCredentialsDto) {
    return keycloakService.getAccessToken(
                keycloakUserCredentialsDto.getUsername(),
                keycloakUserCredentialsDto.getPassword()).getToken();
  }
}
