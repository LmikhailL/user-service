package com.ates.userservice.mapper.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.mapper.UserSavedEventMapper;
import com.ates.userservice.model.UserSavedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserSavedEventMapperImpl implements UserSavedEventMapper {

  @Override
  public UserSavedEvent toEvent(UserEntity savedUser) {
    UserSavedEvent savedEvent = new UserSavedEvent();
    savedEvent.setUserKeycloakId(savedUser.getKeycloakId());
    savedEvent.setFirstName(savedUser.getFirstName());
    savedEvent.setLastName(savedUser.getLastName());
    savedEvent.setRole(savedUser.getRole().getValue());
    return savedEvent;
  }
}
