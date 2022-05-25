package com.colpatria.crudusersapi.user.application;

import com.colpatria.crudusersapi.user.dto.User;
import com.colpatria.crudusersapi.user.infrastructure.ports.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }
}
