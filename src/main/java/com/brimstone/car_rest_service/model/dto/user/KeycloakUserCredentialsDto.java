package com.brimstone.car_rest_service.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserCredentialsDto {

  @NotBlank
  @Schema(defaultValue = "user1")
  private String username;

  @NotBlank
  @Schema(defaultValue = "1")
  private String password;
}
