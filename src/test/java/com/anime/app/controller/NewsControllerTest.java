package com.anime.app.controller;

import com.anime.app.dto.NewsDTO;
import com.anime.app.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {
    private final String URL = "/api/news";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsRepository newsRepository;

    private NewsDTO newsDTO;

    @BeforeEach
    public void setUp() {
        newsDTO = new NewsDTO();
    }

    @Test
    public void createNews() throws Exception{
        Mockito.when(newsRepository.findByAuthorId(Mockito.any())).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL+"/save")
                .content(objectMapper.writeValueAsString(newsDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_CREATED, response.getStatus());
    }
}