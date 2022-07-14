package com.anime.app.controller;

import com.anime.app.domain.News;
import com.anime.app.domain.Role;
import com.anime.app.dto.UserDTO;
import com.anime.app.repository.NewsRepository;
import com.anime.app.repository.RoleRepository;
import com.anime.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  private final String URL = "/api/user";
  private ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RoleRepository roleRepository;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private NewsRepository newsRepository;

  private UserDTO user;
  private Role role;

  private News news;

  @BeforeEach
  void setUp() {
    user = new UserDTO();
    role = new Role();
    role.setId(1L);
    role.setName("ROLE_USER");
    news = new News();
    news.setId(1L);
    news.setTitle("probando esta mierda");
  }

  @Test
  void CreateUser201() throws Exception {
    Mockito.when(userRepository.findUserByEmail(Mockito.any())).thenReturn(null);
    Mockito.when(userRepository.findUserByUserName(Mockito.any())).thenReturn(null);
    Mockito.when(roleRepository.findRoleByName(Mockito.any())).thenReturn(role);
    Mockito.when(newsRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(news));

    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post(URL)
            .content(objectMapper.writeValueAsString(user))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    Assertions.assertEquals(Response.SC_CREATED, response.getStatus());
  }
}
