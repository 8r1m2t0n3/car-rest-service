package com.brimstone.car_rest_service.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Implementation of {@link org.springframework.security.web.AuthenticationEntryPoint} that is used
 * to commence authentication, by delegating to a {@link
 * org.springframework.web.servlet.HandlerExceptionResolver} for handling authentication exceptions.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

  private final HandlerExceptionResolver resolver;

  /**
   * Constructs a new JwtAuthenticationEntryPoint with the specified handler exception resolver.
   *
   * @param resolver the handler exception resolver to be used for resolving exceptions
   */
  public JwtAuthenticationEntryPoint(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
    this.resolver = resolver;
  }

  /**
   * Commences an authentication scheme. In this implementation, it delegates to the configured
   * handler exception resolver to handle the authentication exception.
   *
   * @param request the HTTP servlet request
   * @param response the HTTP servlet response
   * @param authException the authentication exception that caused the invocation
   */
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) {
    resolver.resolveException(request, response, null, authException);
  }
}
