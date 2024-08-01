package com.foxminded.car_rest_service.exception;

import com.foxminded.car_rest_service.exception.model.CustomRuntimeException;
import com.foxminded.car_rest_service.exception.model.ErrorResponse;
import jakarta.validation.constraints.NotNull;
import java.io.StringWriter;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for handling specific exceptions and providing consistent error
 * responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({
    AuthenticationException.class,
    AccessDeniedException.class,
  })
  protected ResponseEntity<ErrorResponse> handleSecurityExceptions(
      Exception ex, ServletWebRequest request) {
    HttpStatus status;
    if (ex instanceof AuthenticationException) {
      status = HttpStatus.UNAUTHORIZED;
    } else {
      status = HttpStatus.FORBIDDEN;
    }
    final ErrorResponse errorResponseBody = buildErrorResponse(ex, request, status);
    writeErrorLog(errorResponseBody);
    return ResponseEntity.status(status).body(errorResponseBody);
  }

  @ExceptionHandler({CustomRuntimeException.class})
  protected ResponseEntity<ErrorResponse> handleCustomExceptions(
      CustomRuntimeException ex, ServletWebRequest request) {
    final HttpStatus status = ex.getHttpStatus();
    final ErrorResponse errorResponseBody = buildErrorResponse(ex, request, status);
    writeErrorLog(errorResponseBody);
    return ResponseEntity.status(status).body(errorResponseBody);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    return super.handleMissingPathVariable(ex, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected @NotNull ResponseEntity<Object> createResponseEntity(
      @Nullable Object body,
      @NotNull HttpHeaders headers,
      HttpStatusCode statusCode,
      @NotNull WebRequest request) {
    final HttpStatus status = HttpStatus.valueOf(statusCode.value());
    final ProblemDetail problemDetail = (ProblemDetail) body;
    final ErrorResponse response = buildErrorResponse(problemDetail, status, request);
    return ResponseEntity.status(status).body(response);
  }

  private ErrorResponse buildErrorResponse(
      final Exception ex, final ServletWebRequest request, final HttpStatus status) {
    final String requestURI = getRequestURI(request);
    return new ErrorResponse(status.value(), ex.getMessage(), requestURI);
  }

  private ErrorResponse buildErrorResponse(
      ProblemDetail detail, HttpStatus status, WebRequest request) {
    final String errorMessage = (detail != null) ? detail.getDetail() : "Unknown error occurred";
    final String requestURI = getRequestURI(request);
    return new ErrorResponse(status.value(), errorMessage, requestURI);
  }

  private String getRequestURI(WebRequest request) {
    if (request instanceof ServletWebRequest) {
      return ((ServletWebRequest) request).getRequest().getRequestURI();
    } else {
      // If WebRequest is not a ServletWebRequest, return a default URI
      return "Unknown URI";
    }
  }

  private void writeErrorLog(ErrorResponse response) {
    logger.error(
        new StringWriter()
            .append("ERROR : ")
            .append(response.getMessage())
            .append(", PATH : ")
            .append(response.getPath())
            .toString());
  }
}
