package com.ates.userservice.kafka.producer;

import com.ates.userservice.model.UserSavedEvent;

public interface UserSavedEventProducer {

  void produce(UserSavedEvent event);
}
