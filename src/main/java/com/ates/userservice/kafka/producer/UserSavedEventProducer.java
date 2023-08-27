package com.ates.userservice.kafka.producer;

import com.avro.events.streaming.UserSavedEvent;

public interface UserSavedEventProducer {

  void produce(UserSavedEvent event);
}
