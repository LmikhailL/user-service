package com.ates.userservice.facade;

import com.avro.events.streaming.UserRegisteredEvent;

public interface UserFacade {

  void on(UserRegisteredEvent event);
}
