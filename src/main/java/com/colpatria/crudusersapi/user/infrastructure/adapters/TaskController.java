package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.user.application.TaskService;
import com.colpatria.crudusersapi.user.dto.Task;
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

  @PostMapping(value = "/{id}/task", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@PathVariable Long id, @Valid @RequestBody Task task) {
    taskService.save(id, task);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/{id}/task", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Set<Task>> findAllByUserId(@PathVariable Long id) {
    return ResponseEntity.ok(taskService.findAllByUserId(id));
  }

  @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Task> findById(@PathVariable Long id) {
    return ResponseEntity.ok(taskService.findById(id));
  }

  @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Task> update(@Valid @RequestBody Task task) {
    return ResponseEntity.ok(taskService.update(task));
  }

  @DeleteMapping(value = "/{id}/task")
  public ResponseEntity<Void> deleteAllByUserId(@PathVariable Long id) {
    taskService.deleteAllByUserId(id);
    return ResponseEntity.ok().build();
  }
}
