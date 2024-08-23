package com.brimstone.car_rest_service.service;

import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {

  AccessTokenResponse getAccessToken(String username, String password);
}
