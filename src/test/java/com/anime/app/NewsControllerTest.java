package com.anime.app;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import com.anime.app.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {
    private final String URL = "/api/news";

    private ObjectMapper objectMapper = new ObjectMapper();

    private final Long NEWS_ID = 1L;

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
    void createNews() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(newsDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_CREATED, response.getStatus());
    }

    @Test
    void updateNews() throws Exception {
        News news = new News();

        Mockito.when(newsRepository.findById(Mockito.any())).thenReturn(Optional.of(news));

        news.setTitle("probando UPDATE LCDTM");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URL)
                .content(objectMapper.writeValueAsString(news))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_OK, response.getStatus());
    }

    @Test
    void deleteNews() throws Exception {
        News news = new News();
        news.setId(NEWS_ID);

        Mockito.when(newsRepository.findById(Mockito.any())).thenReturn(Optional.of(news));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URL + "/" + NEWS_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_OK, response.getStatus());
    }

    @Test
    void getAllNews() throws Exception {
        List<News> newsList = new ArrayList<News>();

        News news = new News();
        news.setId(1L);
        news.setTitle("test pijudo");

        News news2 = new News();
        news2.setId(2L);
        news2.setTitle("test pijudo 2");

        newsList.add(news);
        newsList.add(news2);

        Mockito.when(newsRepository.findAll()).thenReturn(newsList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_OK, response.getStatus());
    }

    @Test
    void getNewsById() throws Exception {
        News news = new News();
        news.setId(NEWS_ID);
        news.setTitle("test pijudo");

        Mockito.when(newsRepository.findById(Mockito.any())).thenReturn(Optional.of(news));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL + "/" + NEWS_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_OK, response.getStatus());
    }
}