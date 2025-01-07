package com.bartemnius.vehiclefleet.authservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class LoginControllerTest {
  @Autowired private MockMvc mockMvc;
  private final String userName = "testUser";
  private final String userPassword = "testUserPa$$word1";

  @BeforeEach
  public void createUser() throws Exception {
    mockMvc
        .perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    String.format(
                        """
                                            {
                                                "username": "%s",
                                                "password": "%s",
                                                "email": "test@email.com",
                                                "phoneNumber": "900100200"
                                            }
                                        """,
                        userName, userPassword)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldAllowLogin() throws Exception {
    String loginRequest =
        String.format(
            """
               {
                    "username": "%s",
                    "password": "%s"
               }
               """,
            userName, userPassword);
    mockMvc
        .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON).content(loginRequest))
        .andExpect(status().isOk());
  }

  @Test
  void userDoesNotExists() throws Exception {
    String loginRequest =
        String.format(
            """
               {
                    "username": "%s",
                    "password": "%s"
               }
               """,
            "badUserName", userPassword);
    mockMvc
        .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON).content(loginRequest))
        .andExpect(status().isNotFound());
  }

  @Test
  void badPassword() throws Exception {
    String loginRequest =
        String.format(
            """
               {
                    "username": "%s",
                    "password": "%s"
               }
               """,
            userName, "badPassword");
    mockMvc
        .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON).content(loginRequest))
        .andExpect(status().isUnauthorized());
  }
}
