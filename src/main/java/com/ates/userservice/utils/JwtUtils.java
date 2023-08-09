package com.ates.userservice.utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

import com.ates.userservice.exception.UnauthorizedException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtils {

  private static final String BEARER_TOKEN_PREFIX = "Bearer ";
  private static final int TOKEN_START_POSITION = 7;
  private static final char DOT = '.';

  public static String getTokenFromRequest(ServletRequest request) {
    String bearer = ((HttpServletRequest) request).getHeader(AUTHORIZATION);

    if (hasText(bearer) && bearer.startsWith(BEARER_TOKEN_PREFIX)) {
      return bearer.substring(TOKEN_START_POSITION);
    } else {
      throw new UnauthorizedException("Token is missing");
    }
  }

  public static String getJwtWithoutSignature(String jwt) {
    final int i = jwt.lastIndexOf(DOT);
    return jwt.substring(0, i + 1);
  }
}
