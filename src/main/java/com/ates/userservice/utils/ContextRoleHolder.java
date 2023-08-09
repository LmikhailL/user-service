package com.ates.userservice.utils;

import com.ates.userservice.model.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContextRoleHolder {

  private static final ThreadLocal<Set<Role>> THREAD_LOCAL = new ThreadLocal<>();

  public static ThreadLocal<Set<Role>> getThreadLocal() {
    return THREAD_LOCAL;
  }

  public static void setUserRoles(final List<Role> userRoles) {
    ContextRoleHolder.getThreadLocal().set(new HashSet<>(userRoles));
  }

  public static List<Role> getRoles() {
    return new ArrayList<>(THREAD_LOCAL.get());
  }

  public static void remove() {
    THREAD_LOCAL.remove();
  }

}
