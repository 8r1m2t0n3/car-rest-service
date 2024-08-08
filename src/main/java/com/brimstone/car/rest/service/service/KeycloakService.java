package com.brimstone.car.rest.service.service;

import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {

  AccessTokenResponse getAccessToken(String username, String password);
}
