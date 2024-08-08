package com.brimstone.car.rest.service.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Lazy
@Getter
@Setter
public class KeycloakClientConfig {

  private String url;
  private String realm;
  private String clientId;
  private String clientSecret;
  private String grantType;

  @Bean
  public Keycloak getKeycloakInstance() {
    return KeycloakBuilder.builder()
        .serverUrl(url)
        .realm(realm)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .build();
  }
}