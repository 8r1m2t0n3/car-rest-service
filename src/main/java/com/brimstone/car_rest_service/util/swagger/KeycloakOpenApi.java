package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.user.KeycloakUserCredentialsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
    name = "Keycloak",
    description =
        "The RESTful API implemented for connecting with Keycloak from the service side only. "
            + "<b>Must not be used beyond the service.</b>")
public interface KeycloakOpenApi {

  @PostMapping("/authenticate")
  @Operation(summary = "Receives JWT token for Keycloak user on username and password")
  ResponseEntity<AccessTokenResponse> getAccessToken(
      @RequestBody KeycloakUserCredentialsDto keycloakUserCredentialsDto);
}
