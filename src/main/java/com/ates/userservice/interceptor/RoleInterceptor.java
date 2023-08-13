package com.ates.userservice.interceptor;

import static com.ates.userservice.utils.ContextRoleHolder.remove;
import static com.ates.userservice.utils.ContextRoleHolder.setUserRoles;
import static com.ates.userservice.utils.JwtUtils.getJwtWithoutSignature;
import static com.ates.userservice.utils.JwtUtils.getTokenFromRequest;
import static java.util.Collections.emptyList;

import com.ates.userservice.model.Role;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RoleInterceptor implements HandlerInterceptor {

  private static final String REALM_ACCESS = "realm_access";
  private static final String ROLES = "roles";
  private static final Set<String> DEFAULT_ROLES = Set.of(
      "offline_access",
      "uma_authorization",
      "default-roles-ates"
  );

  @Override
  public boolean preHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler
  ) {
    try {
      final String token = getTokenFromRequest(request);
      final List<Role> roleList = getRoleListFromJwtToken(token);
      setUserRoles(roleList);
      return true;
    } finally {
      remove();
    }
  }

  private List<Role> getRoleListFromJwtToken(String token) {
    List<String> roles = getRoles(token).stream()
        .filter(role -> !DEFAULT_ROLES.contains(role)).toList();
    return roles.stream().map(Role::fromValue).toList();
  }

  private List<String> getRoles(String token) {
    if (token == null) {
      return emptyList();
    }

    String withoutSignature = getJwtWithoutSignature(token);

    Object roleObj = Jwts.parser()
        .parseClaimsJwt(withoutSignature)
        .getBody()
        .get(REALM_ACCESS, Map.class)
        .get(ROLES);

    return castRoleObjToList(roleObj);
  }

  private List<String> castRoleObjToList(Object roleObj) {
    if (roleObj instanceof List<?> roleList) {
      return roleList.stream()
          .map(RoleInterceptor::toRoleStr)
          .toList();
    } else {
      return emptyList();
    }
  }

  private static String toRoleStr(Object element) {
    return element instanceof String roleValue ? roleValue : null;
  }
}
