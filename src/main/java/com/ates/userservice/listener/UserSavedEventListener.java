package com.ates.userservice.listener;

import com.ates.userservice.kafka.producer.UserSavedEventProducer;
import com.ates.userservice.model.UserSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSavedEventListener {

  private final UserSavedEventProducer userSavedEventProducer;

  @Async
  @TransactionalEventListener
  public void on(UserSavedEvent event) {
    log.info("Started producing UserSavedEvent: {}", event);
    userSavedEventProducer.produce(event);
  }
}
