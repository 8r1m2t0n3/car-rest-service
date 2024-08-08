package com.brimstone.car.rest.service.controller;

import com.brimstone.car.rest.service.model.dto.user.KeycloakUserCredentialsDto;
import com.brimstone.car.rest.service.service.KeycloakService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keycloak")
@Tag(
    name = "Keycloak",
    description =
        "The RESTful API implemented for connecting with Keycloak from the service side only. "
            + "Must not be used beyond the service.")
@AllArgsConstructor
public class KeycloakController {

  private final KeycloakService keycloakService;

  @PostMapping("/authenticate")
  public ResponseEntity<AccessTokenResponse> getAccessToken(
      @RequestBody KeycloakUserCredentialsDto keycloakUserCredentialsDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            keycloakService.getAccessToken(
                keycloakUserCredentialsDto.getUsername(),
                keycloakUserCredentialsDto.getPassword()));
  }
}
