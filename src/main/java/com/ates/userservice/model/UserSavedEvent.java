package com.ates.userservice.model;

import lombok.Data;

@Data
public class UserSavedEvent {

  private String userKeycloakId;
  private String firstName;
  private String lastName;
}
