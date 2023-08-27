package com.ates.userservice.mapper.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.mapper.UserSavedEventMapper;
import org.springframework.stereotype.Component;
import com.avro.events.streaming.UserSavedEvent;

@Component
public class UserSavedEventMapperImpl implements UserSavedEventMapper {

  @Override
  public UserSavedEvent toEvent(UserEntity savedUser) {
    UserSavedEvent savedEvent = new UserSavedEvent();
    savedEvent.setUserKeycloakId(savedUser.getKeycloakId());
    savedEvent.setFirstName(savedUser.getFirstName());
    savedEvent.setLastName(savedUser.getLastName());
    savedEvent.setRole(savedUser.getRole().name());
    return savedEvent;
  }
}
