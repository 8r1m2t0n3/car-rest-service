package com.brimstone.car_rest_service.util.swagger;

import com.brimstone.car_rest_service.model.dto.user.KeycloakUserCredentialsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Keycloak",
    description =
        "The RESTful API to connect with Keycloak from the service side only. "
            + "<b>Must not be used beyond the service!</b>")
public interface KeycloakOpenApi {

  @Operation(summary = "Retrieves JWT token for Keycloak user on username and password")
  String getAccessToken(KeycloakUserCredentialsDto keycloakUserCredentialsDto);
}
