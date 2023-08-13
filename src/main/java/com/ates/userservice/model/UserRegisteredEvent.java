package com.ates.userservice.model;

import lombok.Data;

@Data
public class UserRegisteredEvent {

  private String id;
  private String firstName;
  private String lastName;
  private String email;
}
