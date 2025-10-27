package ua.edu.ucu.apps.lab7.lab7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import ua.edu.ucu.apps.lab7.controller.PaymentController;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPaymentsEndpoint() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", containsString("Credit Card Payment")))
                .andExpect(jsonPath("$[1]", containsString("PayPal Payment")));
    }

    @Test
    void testGetPaymentsReturnsList() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetPaymentsContainsCreditCardInfo() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", containsString("1200")))
                .andExpect(jsonPath("$[0]", containsString("UAH")));
    }

    @Test
    void testGetPaymentsContainsPayPalInfo() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1]", containsString("PayPal")))
                .andExpect(jsonPath("$[1]", containsString("1200")));
    }

    @Test
    void testGetPaymentsReturnsCorrectMessageFormat() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", 
                    is("Credit Card Payment processed for 1200.0 UAH")))
                .andExpect(jsonPath("$[1]", 
                    is("PayPal Payment processed for 1200.0 UAH")));
    }

    @Test
    void testPaymentsEndpointAccessibility() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk());
    }

    @Test
    void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/payment"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPaymentsWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/payments")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}