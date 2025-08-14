package org.example.dpprojects.controller;

import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddPublisher() throws Exception {
        String publisherJson = "{ \"name\": \"ABC Publishing\", \"address\": \"123 Main St\" }";
        mockMvc.perform(post("/api/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(publisherJson))
                .andExpect(status().isOk());
    }
}
