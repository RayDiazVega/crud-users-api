package com.colpatria.crudusersapi.user.infrastructure.adapters;

import com.colpatria.crudusersapi.constant.Constant;
import java.time.LocalDate;
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
class AUserControllerTest {

  @Autowired
  MockMvc mockMvc;

  String user = Constant.user;

  @Test
  @Order(1)
  void saveStatus200() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.email").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(1)
  void saveStatus400() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.error").value("¡User email already exists!"),
            MockMvcResultMatchers.jsonPath("$.message").value("Client error"))
        .andDo(MockMvcResultHandlers.print());

    user = user.replace("Poracius", "1");

    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.error.[0]").value("names must be a valid value"),
            MockMvcResultMatchers.jsonPath("$.message").value("Client error"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  void findAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.totalElements").isNumber(),
            MockMvcResultMatchers.jsonPath("$.content").isArray())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(3)
  void findById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.email").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(4)
  void findByEmail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/email/poraciusyettengia@imgur.com"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.email").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(5)
  void findByCreatedDateBetween() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/createdDate/{from}/{to}",
            LocalDate.now() + "T00:00:00", LocalDate.now() + "T23:59:59"))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.[0].id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.[0].email").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(6)
  void update() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put("/users")
            .contentType(MediaType.APPLICATION_JSON).content(user))
        .andExpectAll(MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
            MockMvcResultMatchers.jsonPath("$.id").isNumber(),
            MockMvcResultMatchers.jsonPath("$.email").isString())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(7)
  void deleteById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(8)
  void deleteAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/users"))
        .andExpectAll(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}