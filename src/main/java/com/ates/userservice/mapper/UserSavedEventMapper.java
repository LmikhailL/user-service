package com.ates.userservice.mapper;

import com.ates.userservice.entity.UserEntity;
import com.avro.events.streaming.UserSavedEvent;

public interface UserSavedEventMapper {

  UserSavedEvent toEvent(UserEntity savedUser);
}
