package com.ates.userservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

  ADMIN_POPUG("ADMIN_POPUG"),
  DEFAULT_POPUG("DEFAULT_POPUG"),
  MANAGER_POPUG("MANAGER_POPUG");

  private final String value;

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  @JsonCreator
  public static Role fromValue(final String value) {
    try {
      return Role.valueOf(value);
    } catch (Exception ex) {
      throw new IllegalArgumentException("Unexpected value: " + value);
    }
  }
}
