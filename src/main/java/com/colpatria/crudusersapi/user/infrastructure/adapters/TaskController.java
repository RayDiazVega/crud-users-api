package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.user.application.TaskService;
import com.colpatria.crudusersapi.user.dto.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Operation(summary = "Create task", parameters = @Parameter(name = "userId", example = "1"))
  @PostMapping("/{userId}/tasks")
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@PathVariable Long userId, @Valid @RequestBody Task task) {
    taskService.save(userId, task);
  }

  @Operation(summary = "Find tasks by user id", parameters = @Parameter(name = "userId", example = "1"))
  @GetMapping("/{userId}/tasks")
  public Set<Task> findAllByUserId(@PathVariable Long userId) {
    return taskService.findAllByUserId(userId);
  }

  @Operation(summary = "Find task by id", parameters = @Parameter(name = "taskId", example = "1"))
  @GetMapping("/tasks/{taskId}")
  public Task findById(@PathVariable Long taskId) {
    return taskService.findById(taskId);
  }

  @Operation(summary = "Update task")
  @PutMapping("/tasks")
  public Task update(@Valid @RequestBody Task task) {
    return taskService.update(task);
  }

  @Operation(summary = "Delete task by id", parameters = @Parameter(name = "taskId", example = "1"))
  @DeleteMapping("/tasks/{taskId}")
  public void deleteById(@PathVariable Long taskId) {
    taskService.deleteById(taskId);
  }
}
