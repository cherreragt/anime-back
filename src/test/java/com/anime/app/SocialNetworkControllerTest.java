package com.anime.app;

import com.anime.app.domain.SocialNetwork;
import com.anime.app.repository.SocialNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SocialNetworkControllerTest {
    private final String URL = "/api/social-network";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocialNetworkRepository socialNetworkRepository;

    @Test
    void getAllActiveSocialNetworks() throws Exception {
        List<SocialNetwork> socialNetworkList = new ArrayList<SocialNetwork>();

        SocialNetwork socialnetwork = new SocialNetwork();
        socialnetwork.setId(1L);
        socialnetwork.setName("Facebook");
        socialnetwork.setActive(true);

        SocialNetwork socialnetwork2 = new SocialNetwork();
        socialnetwork2.setId(2L);
        socialnetwork2.setName("Instagram");
        socialnetwork2.setActive(true);

        SocialNetwork socialnetwork3 = new SocialNetwork();
        socialnetwork3.setId(3L);
        socialnetwork3.setName("Reddit");
        socialnetwork3.setActive(false);

        socialNetworkList.add(socialnetwork);
        socialNetworkList.add(socialnetwork2);
        socialNetworkList.add(socialnetwork3);

        Mockito.when(socialNetworkRepository.findAllByActive(Mockito.anyBoolean())).thenReturn(socialNetworkList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assertions.assertEquals(Response.SC_OK, response.getStatus());
    }
}