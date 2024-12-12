package com.bartemnius.vehiclefleet.auth_service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
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
class RegistrationControllerTest {
  @Autowired private MockMvc mockMvc;

  @Test
  void shouldAllowRegistration() throws Exception {
    String request = createUser("testUser", "Password123!", "email@email.com", "123123123");

    mockMvc
        .perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(request))
        .andExpect(status().isCreated());
  }

  @Test
  void badRegistration_UserAlreadyExists() throws Exception {
    String userAlreadyExists =
        createUser("testUser", "Password123!", "email@email.com", "123123123");

    mockMvc.perform(
        post("/api/register").contentType(MediaType.APPLICATION_JSON).content(userAlreadyExists));
    mockMvc
        .perform(
            (post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userAlreadyExists)))
        .andExpect(status().isConflict());
  }

  @Test
  void badRegistration_BadEmail() throws Exception {
    String user = createUser("testUser", "Password123!", "email@email.com", "123123123");
    String sameEmailUser = createUser("testUser2", "Password123!", "email@email.com", "123123124");

    mockMvc.perform(post("/api/register").contentType(MediaType.APPLICATION_JSON).content(user));
    mockMvc
        .perform(
            (post("/api/register").contentType(MediaType.APPLICATION_JSON).content(sameEmailUser)))
        .andExpect(status().isConflict())
        .andExpect(content().string("User with this email already registered!"));
  }

  private String createUser(String username, String password, String mail, String phoneNumber) {
    return String.format(
        """
            {
                "username": "%s",
                "password": "%s",
                "email": "%s",
                "phoneNumber": "%s"
            }
        """,
        username, password, mail, phoneNumber);
  }
}
