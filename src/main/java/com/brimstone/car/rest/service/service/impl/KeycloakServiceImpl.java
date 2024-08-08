package com.brimstone.car.rest.service.service.impl;

import com.brimstone.car.rest.service.config.KeycloakClientConfig;
import com.brimstone.car.rest.service.exception.keycloak.KeycloakUserProvideInvalidCredentialsException;
import com.brimstone.car.rest.service.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

  private final RestTemplate restTemplate = new RestTemplate();
  private final KeycloakClientConfig keycloakConfig;

  @Override
  public AccessTokenResponse getAccessToken(String username, String password) {
    final MultiValueMap<String, String> parameters =
        createAccessTokenParameters(username, password);

    final HttpEntity<MultiValueMap<String, String>> requestEntity =
        new HttpEntity<>(parameters, createHeaders());

    try {
      final ResponseEntity<AccessTokenResponse> responseEntity =
          restTemplate.exchange(
              getAuthUrl(), HttpMethod.POST, requestEntity, AccessTokenResponse.class);
      return responseEntity.getBody();
    } catch (HttpClientErrorException cause) {
      throw new KeycloakUserProvideInvalidCredentialsException();
    }
  }

  private HttpHeaders createHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }

  private MultiValueMap<String, String> createAccessTokenParameters(
      String username, String password) {
    final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
    parameters.add("username", username);
    parameters.add("password", password);
    parameters.add("grant_type", "password");
    parameters.add("client_id", keycloakConfig.getClientId());
    parameters.add("client_secret", keycloakConfig.getClientSecret());
    return parameters;
  }

  private String getAuthUrl() {
    return UriComponentsBuilder.fromHttpUrl(keycloakConfig.getUrl())
        .pathSegment("realms")
        .pathSegment(keycloakConfig.getRealm())
        .pathSegment("protocol")
        .pathSegment("openid-connect")
        .pathSegment("token")
        .toUriString();
  }
}
