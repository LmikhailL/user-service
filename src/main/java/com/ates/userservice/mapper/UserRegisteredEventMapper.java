package com.ates.userservice.mapper;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.model.UserRegisteredEvent;

public interface UserRegisteredEventMapper {

  UserEntity toUser(UserRegisteredEvent event);
}
