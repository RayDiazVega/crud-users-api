package com.colpatria.crudusersapi.user.application;

import com.colpatria.crudusersapi.user.dto.Task;
import com.colpatria.crudusersapi.user.dto.User;
import com.colpatria.crudusersapi.user.infrastructure.ports.TaskRepository;
import com.colpatria.crudusersapi.user.infrastructure.ports.UserRepository;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  public void save(Long userId, Task task) {
    task.setId(0L);
    User user = userRepository.findById(userId).orElseThrow();
    user.getTasks().add(task);
    log.info("Task created");
    userRepository.save(user);
  }

  public Set<Task> findAllByUserId(Long userId) {
    log.info("Find task by user id");
    return userRepository.findById(userId).orElseThrow().getTasks();
  }

  public Task findById(Long taskId) {
    log.info("Find task by id");
    return taskRepository.findById(taskId).orElseThrow();
  }

  public Task update(Task task) {
    findById(task.getId());
    log.info("Task updated");
    return taskRepository.save(task);
  }

  public void deleteById(Long taskId) {
    log.info("Delete tasks by user id");
    taskRepository.deleteById(taskId);
  }
}
