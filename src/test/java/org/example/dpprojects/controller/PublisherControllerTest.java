package org.example.dpprojects.controller;

import org.junit.jupiter.api.Test; // Изменено с TestNG на JUnit
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllPublishers() throws Exception {
        mockMvc.perform(get("/api/publishers"))
                .andExpect(status().isOk());
    }
}