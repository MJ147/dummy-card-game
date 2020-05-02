package com.mj147.controller.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifCreatePlayerRequestReturn200AndId() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"MJ1\",\n" +
                        "\t\"sex\": \"MALE\",\n" +
                        "\t\"age\": 18\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void ifIncorrectCreatePlayerRequestReturn400AndException() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"MJ1\",\n" +
                        "\t\"sex\": \"MALE\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR001\",\n" +
                        "\t\"errorMsg\": \"Incorrect request data.\"\n" +
                        "}")
                );
    }
}
