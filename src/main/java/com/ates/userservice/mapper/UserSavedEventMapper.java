package com.ates.userservice.mapper;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.model.UserSavedEvent;

public interface UserSavedEventMapper {

  UserSavedEvent toEvent(UserEntity savedUser);
}
