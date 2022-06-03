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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Create user")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User save(@Valid @RequestBody User user) {
    return userService.save(user);
  }

  @Operation(summary = "Find all users")
  @GetMapping
  public Page<User> findAll(
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "10", required = false) int size,
      @RequestParam(defaultValue = "names", required = false) String sortBy) {
    return userService.findAll(page, size, sortBy);
  }

  @Operation(summary = "Find user by id", parameters = @Parameter(name = "id", example = "1"))
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @Operation(summary = "Find user by email", parameters = @Parameter(name = "email", example = "poraciusyettengia@imgur.com"))
  @GetMapping("/email/{email}")
  public User findByEmail(@PathVariable String email) {
    return userService.findByEmail(email);
  }

  @Operation(summary = "Find users between date range", parameters = {
      @Parameter(name = "from", example = "2022-05-26T00:00:00"),
      @Parameter(name = "to", example = "2022-05-26T23:59:59")})
  @GetMapping("/createdDate/{from}/{to}")
  public List<User> findByCreatedDateBetween(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to) {
    return userService.findByCreatedDateBetween(from, to);
  }

  @Operation(summary = "Update user")
  @PutMapping
  public User update(@Valid @RequestBody User user) {
    return userService.update(user);
  }

  @Operation(summary = "Delete user by id", parameters = @Parameter(name = "id", example = "1"))
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }

  @Operation(summary = "Delete all user")
  @DeleteMapping
  public void deleteAll() {
    userService.deleteAll();
  }
}
