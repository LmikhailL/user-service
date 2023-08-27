package com.ates.userservice.facade.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.facade.UserFacade;
import com.ates.userservice.mapper.UserRegisteredEventMapper;
import com.ates.userservice.mapper.UserSavedEventMapper;
import com.ates.userservice.service.UserService;
import com.avro.events.streaming.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.avro.events.streaming.UserSavedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;
  private final UserRegisteredEventMapper userRegisteredEventMapper;
  private final UserSavedEventMapper userSavedEventMapper;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
  public void on(UserRegisteredEvent event) {
    log.info("Started user data replication, event: {}", event);

    UserEntity user = userRegisteredEventMapper.toUser(event);
    UserEntity savedUser = userService.save(user);
    UserSavedEvent savedEvent = userSavedEventMapper.toEvent(savedUser);

    eventPublisher.publishEvent(savedEvent);
  }
}
