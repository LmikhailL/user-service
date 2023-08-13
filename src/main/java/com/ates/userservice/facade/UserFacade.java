package com.ates.userservice.facade;

import com.ates.userservice.model.UserRegisteredEvent;

public interface UserFacade {

  void on(UserRegisteredEvent event);
}
