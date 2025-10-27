package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Lab7ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testListEndpointExists() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testListEndpointReturnsJsonArray() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testListEndpointReturnsTenUUIDs() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void testListEndpointReturnsStrings() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isString())
                .andExpect(jsonPath("$[1]").isString())
                .andExpect(jsonPath("$[2]").isString());
    }

    @Test
    void testListEndpointReturnsValidUUIDFormat() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(matchesRegex(
                    "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    @Test
    void testListEndpointReturnsDifferentUUIDs() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(not(equalTo(jsonPath("$[1]").value(null)))));
    }

    @Test
    void testListEndpointWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/list")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testListEndpointIsIdempotent() throws Exception {
        // Make two requests to verify endpoint is accessible multiple times
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
        
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void testListEndpointAllElementsAreStrings() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", everyItem(isA(String.class))));
    }
}