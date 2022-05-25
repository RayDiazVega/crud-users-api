package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.user.application.UserService;
import com.colpatria.crudusersapi.user.dto.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> save(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<User>> findAll(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size,
      @RequestParam(defaultValue = "names", required = false) String sortBy) {
    return ResponseEntity.ok(userService.findAll(page, size, sortBy));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @GetMapping(value = "/{email}/email", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findByEmail(@PathVariable String email) {
    return ResponseEntity.ok(userService.findByEmail(email));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> update(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.update(user));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    userService.deleteAll();
    return ResponseEntity.ok().build();
  }
}
