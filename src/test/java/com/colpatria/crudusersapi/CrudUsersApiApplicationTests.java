package com.colpatria.crudusersapi;

import com.colpatria.crudusersapi.user.application.TaskService;
import com.colpatria.crudusersapi.user.application.UserService;
import com.colpatria.crudusersapi.user.infrastructure.adapters.TaskController;
import com.colpatria.crudusersapi.user.infrastructure.adapters.UserController;
import com.colpatria.crudusersapi.user.infrastructure.ports.TaskRepository;
import com.colpatria.crudusersapi.user.infrastructure.ports.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor(onConstructor_ = {@Autowired})
class CrudUsersApiApplicationTests {

	TaskRepository taskRepository;
	TaskService taskService;
	TaskController taskController;
	UserRepository userRepository;
	UserService userService;
	UserController userController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(taskRepository);
		Assertions.assertNotNull(taskService);
		Assertions.assertNotNull(taskController);
		Assertions.assertNotNull(userRepository);
		Assertions.assertNotNull(userService);
		Assertions.assertNotNull(userController);
	}
}
