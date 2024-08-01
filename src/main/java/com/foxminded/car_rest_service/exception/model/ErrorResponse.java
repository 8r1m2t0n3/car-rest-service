package com.foxminded.car_rest_service.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Data;

/**
 * Represents an error response returned by the API. Contains information about the error message,
 * timestamp, path, and status code.
 */
@Data
public class ErrorResponse {

  @Schema(
      description = "Message",
      example = "Full authentication is required to access this resource")
  private String message;

  @Schema(description = "Time when received response", example = "2023-01-01 00:00:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;

  @Schema(description = "Path", example = "/api/students")
  private String path;

  @Schema(description = "Status code", example = "401")
  private Integer status;

  public ErrorResponse(Integer status, String message, String path) {
    timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
    this.path = path;
  }
}
