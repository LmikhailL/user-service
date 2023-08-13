package com.ates.userservice.service.impl;

import com.ates.userservice.entity.UserEntity;
import com.ates.userservice.repository.UserEntityRepository;
import com.ates.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserEntityRepository userRepository;

  @Override
  @Transactional
  public UserEntity save(UserEntity user) {
    log.info("Started saving or updating user: {}", user);
    return userRepository.save(user);
  }
}
