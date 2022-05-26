package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.constant.Constant;
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
@TestMethodOrder(OrderAnnotation.class)
class BTaskControllerTest {

  @Autowired
  MockMvc mockMvc;

  String user = Constant.user;

  String task = Constant.task;

  @Test
  @Order(1)
  void saveStatus200() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

    mockMvc.perform(MockMvcRequestBuilders.post("/user/2/task")
            .contentType(MediaType.APPLICATION_JSON).content(task))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  void saveStatus405() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/user/task"))
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
    mockMvc.perform(MockMvcRequestBuilders.get("/user/2/task"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.[0].id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.[0].name").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(4)
  void findById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/user/task/3"))
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
    mockMvc.perform(MockMvcRequestBuilders.put("/user/task")
            .contentType(MediaType.APPLICATION_JSON).content(task))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.content().json(task))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(6)
  void deleteById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/user/task/3"))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}