package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.constant.Constant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("2-TaskControllerTest")
@TestMethodOrder(OrderAnnotation.class)
class TaskControllerTest {

  @Autowired
  MockMvc mockMvc;

  String user = Constant.user;

  String task = Constant.task;

  @Test
  @Order(1)
  void saveStatus201() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());

    mockMvc.perform(MockMvcRequestBuilders.post("/users/2/tasks")
            .contentType(MediaType.APPLICATION_JSON).content(task))
        .andExpectAll(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  void saveStatus405() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/users/tasks"))
        .andExpectAll(MockMvcResultMatchers.status().isMethodNotAllowed(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.error")
                .value("Request method 'PATCH' not supported"),
            MockMvcResultMatchers.jsonPath("$.message").value("Client error"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(3)
  void findAllByUserId() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/2/tasks"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.[0].id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.[0].name").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(4)
  void findById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/tasks/3"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.name").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(5)
  void update() throws Exception {
    task = task.replace("1", "3");
    mockMvc.perform(MockMvcRequestBuilders.put("/users/tasks")
            .contentType(MediaType.APPLICATION_JSON).content(task))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.content().json(task))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(6)
  void deleteById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/users/tasks/3"))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}