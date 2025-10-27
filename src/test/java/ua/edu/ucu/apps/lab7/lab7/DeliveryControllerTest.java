package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ua.edu.ucu.apps.lab7.controller.DeliveryController;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Post Delivery Tests
    @Test
    void testPostDeliveryWithLowPrice() throws Exception {
        mockMvc.perform(get("/delivery/post")
                .param("orderPrice", "500"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    void testPostDeliveryWithHighPrice() throws Exception {
        mockMvc.perform(get("/delivery/post")
                .param("orderPrice", "1500"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void testPostDeliveryAtThreshold() throws Exception {
        mockMvc.perform(get("/delivery/post")
                .param("orderPrice", "1000"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    void testPostDeliveryJustAboveThreshold() throws Exception {
        mockMvc.perform(get("/delivery/post")
                .param("orderPrice", "1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    // DHL Delivery Tests
    @Test
    void testDHLDeliveryWithLowPrice() throws Exception {
        mockMvc.perform(get("/delivery/dhl")
                .param("orderPrice", "1000"))
                .andExpect(status().isOk())
                .andExpect(content().string("200.0"));
    }

    @Test
    void testDHLDeliveryWithHighPrice() throws Exception {
        mockMvc.perform(get("/delivery/dhl")
                .param("orderPrice", "3000"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void testDHLDeliveryAtThreshold() throws Exception {
        mockMvc.perform(get("/delivery/dhl")
                .param("orderPrice", "2000"))
                .andExpect(status().isOk())
                .andExpect(content().string("200.0"));
    }

    @Test
    void testDHLDeliveryJustAboveThreshold() throws Exception {
        mockMvc.perform(get("/delivery/dhl")
                .param("orderPrice", "2001"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void testPostDeliveryWithZeroPrice() throws Exception {
        mockMvc.perform(get("/delivery/post")
                .param("orderPrice", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    void testDHLDeliveryWithZeroPrice() throws Exception {
        mockMvc.perform(get("/delivery/dhl")
                .param("orderPrice", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("200.0"));
    }
}