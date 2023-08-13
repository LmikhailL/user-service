package com.ates.userservice.facade.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.facade.UserFacade;
import com.ates.userservice.mapper.UserRegisteredEventMapper;
import com.ates.userservice.model.UserRegisteredEvent;
import com.ates.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;
  private final UserRegisteredEventMapper userRegisteredEventMapper;

  @Override
  @Transactional
  public void on(UserRegisteredEvent event) {
    log.info("Started user data replication, event: {}", event);

    UserEntity user = userRegisteredEventMapper.toUser(event);
    userService.save(user);
    //TODO UserSavedEvent
  }
}
