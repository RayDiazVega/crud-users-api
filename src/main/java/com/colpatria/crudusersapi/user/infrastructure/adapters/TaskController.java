package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.user.application.TaskService;
import com.colpatria.crudusersapi.user.dto.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Operation(summary = "Create task", parameters = {
      @Parameter(name = "userId", description = "Example value for user id", example = "1")})
  @PostMapping(value = "/{userId}/task", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@PathVariable Long userId, @Valid @RequestBody Task task) {
    taskService.save(userId, task);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Find tasks by user id", parameters = {
      @Parameter(name = "userId", description = "Example value for user id", example = "1")})
  @GetMapping(value = "/{userId}/task", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Set<Task>> findAllByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(taskService.findAllByUserId(userId));
  }

  @Operation(summary = "Find task by id", parameters = {
      @Parameter(name = "taskId", description = "Example value for task id", example = "1")})
  @GetMapping(value = "/task/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Task> findById(@PathVariable Long taskId) {
    return ResponseEntity.ok(taskService.findById(taskId));
  }

  @Operation(summary = "Update task")
  @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Task> update(@Valid @RequestBody Task task) {
    return ResponseEntity.ok(taskService.update(task));
  }

  @Operation(summary = "Delete task by id", parameters = {
      @Parameter(name = "taskId", description = "Example value for task id", example = "1")})
  @DeleteMapping(value = "/task/{taskId}")
  public ResponseEntity<Void> deleteById(@PathVariable Long taskId) {
    taskService.deleteById(taskId);
    return ResponseEntity.ok().build();
  }
}
