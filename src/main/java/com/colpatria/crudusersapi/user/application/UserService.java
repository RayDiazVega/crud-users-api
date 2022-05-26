package com.colpatria.crudusersapi.user.application;

import com.colpatria.crudusersapi.user.dto.User;
import com.colpatria.crudusersapi.user.infrastructure.ports.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      throw new IllegalArgumentException("¡User email already exists!");
    }
    user.setId(0L);
    log.info("User created");
    return userRepository.save(user);
  }

  public Page<User> findAll(int page, int size, String sortBy) {
    log.info("Find all users");
    return userRepository.findAll(PageRequest.of(page, size, Direction.ASC, sortBy));
  }

  public User findById(Long id) {
    log.info("Find user by id");
    return userRepository.findById(id).orElseThrow();
  }

  public User findByEmail(String email) {
    log.info("Find user by email");
    return userRepository.findByEmail(email).orElseThrow();
  }

  public List<User> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to) {
    log.info("Find users by created date between date range");
    return userRepository.findByCreatedDateBetween(from, to);
  }

  public User update(User user) {
    if (!user.getEmail().equalsIgnoreCase(findById(user.getId()).getEmail())) {
      if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        throw new IllegalArgumentException("¡User email already exists!");
      }
    }
    log.info("User updated");
    return userRepository.save(user);
  }

  public void deleteById(Long id) {
    findById(id);
    log.info("User deleted");
    userRepository.deleteById(id);
  }

  public void deleteAll() {
    log.info("Delete all users");
    userRepository.deleteAll();
  }
}
