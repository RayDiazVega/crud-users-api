package com.colpatria.crudusersapi.user.application;

import com.colpatria.crudusersapi.user.dto.User;
import com.colpatria.crudusersapi.user.infrastructure.ports.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
    Optional<User> userFind = userRepository.findByEmail(user.getEmail());
    if (userFind.isPresent()) {
      throw new IllegalArgumentException("¡User email already exists!");
    }
    user.setId(null);
    log.info("User created");
    return userRepository.save(user);
  }

  public Page<User> findAll(int page, int size, String sortBy) {
    log.info("Find all users");
    return userRepository.findAll(PageRequest.of(page, size, Direction.ASC, sortBy));
  }

  public User findById(Long id) {
    log.info("Find by id");
    return userRepository.findById(id).orElseThrow();
  }

  public User findByEmail(String email) {
    log.info("Find by email");
    return userRepository.findByEmail(email).orElseThrow();
  }

  public List<User> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to) {
    return userRepository.findByCreatedDateBetween(from, to);
  }

  public User update(User user) {
    User userFind = findById(user.getId());
    if(!user.getEmail().equalsIgnoreCase(userFind.getEmail())) {
      Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
      if (optionalUser.isPresent()) {
        throw new IllegalArgumentException("¡User email already exists!");
      }
    }
    log.info("User updated");
    return userRepository.save(user);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }

  @PostConstruct
  public void insert() {
    for (int i = 0; i < 50; i++) {
      String generatedString = RandomStringUtils.randomAlphabetic(6);
      User user = new User();
      user.setNames(generatedString);
      user.setSurnames(generatedString);
      user.setEmail(generatedString + "@" + generatedString);
      save(user);
    }
  }
}
