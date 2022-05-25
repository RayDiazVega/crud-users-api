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

  public void save(Long id, Task task) {
    task.setId(null);
    User user = userRepository.findById(id).orElseThrow();
    user.getTasks().add(task);
    log.info("Task created");
    userRepository.save(user);
  }

  public Set<Task> findAllByUserId(Long id) {
    return userRepository.findById(id).orElseThrow().getTasks();
  }

  public Task findById(Long id) {
    return taskRepository.findById(id).orElseThrow();
  }

  public Task update(Task task) {
    findById(task.getId());
    log.info("Task updated");
    return taskRepository.save(task);
  }

  public void deleteAllByUserId(Long id) {
    taskRepository.deleteAll(findAllByUserId(id));
  }
}
