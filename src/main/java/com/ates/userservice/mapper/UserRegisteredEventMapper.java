package com.ates.userservice.mapper;

import com.ates.userservice.entity.UserEntity;
import com.avro.events.streaming.UserRegisteredEvent;

public interface UserRegisteredEventMapper {

  UserEntity toUser(UserRegisteredEvent event);
}
