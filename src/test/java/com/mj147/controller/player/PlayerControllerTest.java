package com.mj147.controller.player;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * CREATE PLAYER REQUEST TESTS
     **/
    @Test
    public void ifCreatePlayerRequestIsCorrectReturn200AndId() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"Name1\",\n" +
                        "\t\"sex\": \"MALE\",\n" +
                        "\t\"age\": 18\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesPattern("[0-9]+")));
    }

    @Test
    public void ifCreatePlayerRequestIsIncorrectReturn400AndERR001() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"Name1\",\n" +
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

    @Test
    public void ifNameIsAlreadyTakenCreatePlayerRequestReturn400AndERR003() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"TakenName\",\n" +
                        "\t\"sex\": \"MALE\",\n" +
                        "\t\"age\": 18\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"name\": \"TakenName\",\n" +
                        "\t\"sex\": \"MALE\",\n" +
                        "\t\"age\": 18\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\n" +
                        "\t\"errorCode\": \"ERR003\",\n" +
                        "\t\"errorMsg\": \"Player name is already taken.\"\n" +
                        "}")
                );
    }

    /**
     * GET PLAYER REQUEST TESTS
     **/
    @Test
    public void ifGetPlayerRequestIsCorrectReturn200AndBody() throws Exception {
        mockMvc.perform(post("/player/")
                .content("{\n" +
                        "\t\"id\": \"10\",\n" +
                        "\t\"name\": \"Name2\",\n" +
                        "\t\"sex\": \"MALE\",\n" +
                        "\t\"age\": 18\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(get("/player/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void ifIdNotExistsGetPlayerRequestReturn400andERR002() throws Exception {
        mockMvc.perform(get("/player/{id}", 100))
                .andExpect(status().is(409))
                .andExpect(content().json("{\n" +
                        "    \"errorCode\": \"ERR002\",\n" +
                        "    \"errorMsg\": \"Entity does not exist.\"\n" +
                        "}")
                );
    }
}
