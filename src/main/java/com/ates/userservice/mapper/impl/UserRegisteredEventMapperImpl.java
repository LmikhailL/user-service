package com.ates.userservice.mapper.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.mapper.UserRegisteredEventMapper;
import com.avro.events.streaming.UserRegisteredEvent;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventMapperImpl implements UserRegisteredEventMapper {

  @Override
  public UserEntity toUser(UserRegisteredEvent event) {
    UserEntity user = new UserEntity();
    user.setKeycloakId(event.getId());
    user.setEmail(event.getEmail());
    user.setFirstName(event.getFirstName());
    user.setLastName(event.getLastName());
    return user;
  }
}
