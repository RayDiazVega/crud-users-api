package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.user.application.UserService;
import com.colpatria.crudusersapi.user.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Create user")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> save(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }

  @Operation(summary = "Find all users")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<User>> findAll(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size,
      @RequestParam(defaultValue = "names", required = false) String sortBy) {
    return ResponseEntity.ok(userService.findAll(page, size, sortBy));
  }

  @Operation(summary = "Find user by id", parameters = {
      @Parameter(name = "id", description = "Example value for id", example = "1")})
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @Operation(summary = "Find user by email", parameters = {
      @Parameter(name = "email", description = "Example value for email", example = "poraciusyettengia@imgur.com")})
  @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findByEmail(@PathVariable String email) {
    return ResponseEntity.ok(userService.findByEmail(email));
  }

  @Operation(summary = "Find users between date range ", parameters = {
      @Parameter(name = "from", description = "Example value for from", example = "2022-05-26T00:00:00"),
      @Parameter(name = "to", description = "Example value for to", example = "2022-05-26T23:59:59")})
  @GetMapping(value = "/createdDate/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> findByCreatedDateBetween(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to) {
    return ResponseEntity.ok(userService.findByCreatedDateBetween(from, to));
  }

  @Operation(summary = "Update user")
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> update(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.update(user));
  }

  @Operation(summary = "Delete user by id", parameters = {
      @Parameter(name = "id", description = "Example value for id", example = "1")})
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Delete all user")
  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    userService.deleteAll();
    return ResponseEntity.ok().build();
  }
}
