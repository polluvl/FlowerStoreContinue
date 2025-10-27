package ua.edu.ucu.apps.lab7.lab7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab7.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab7.delivery.Delivery;
import ua.edu.ucu.apps.lab7.delivery.PostDeliveryStrategy;

class DeliveryStrategyTest {

    // Post Delivery Strategy Tests
    @Test
    void testPostDeliveryBelowThreshold() {
        Delivery delivery = new PostDeliveryStrategy();
        assertEquals(100, delivery.delivery(500));
    }

    @Test
    void testPostDeliveryAtThreshold() {
        Delivery delivery = new PostDeliveryStrategy();
        assertEquals(100, delivery.delivery(1000));
    }

    @Test
    void testPostDeliveryAboveThreshold() {
        Delivery delivery = new PostDeliveryStrategy();
        assertEquals(0, delivery.delivery(1001));
    }

    @Test
    void testPostDeliveryWellAboveThreshold() {
        Delivery delivery = new PostDeliveryStrategy();
        assertEquals(0, delivery.delivery(5000));
    }

    @Test
    void testPostDeliveryWithZero() {
        Delivery delivery = new PostDeliveryStrategy();
        assertEquals(100, delivery.delivery(0));
    }

    // DHL Delivery Strategy Tests
    @Test
    void testDHLDeliveryBelowThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        assertEquals(200, delivery.delivery(1000));
    }

    @Test
    void testDHLDeliveryAtThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        assertEquals(200, delivery.delivery(2000));
    }

    @Test
    void testDHLDeliveryAboveThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        assertEquals(0, delivery.delivery(2001));
    }

    @Test
    void testDHLDeliveryWellAboveThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        assertEquals(0, delivery.delivery(10000));
    }

    @Test
    void testDHLDeliveryWithZero() {
        Delivery delivery = new DHLDeliveryStrategy();
        assertEquals(200, delivery.delivery(0));
    }

    // Comparison Tests
    @Test
    void testPostCheaperThanDHLBelowBothThresholds() {
        Delivery post = new PostDeliveryStrategy();
        Delivery dhl = new DHLDeliveryStrategy();
        
        double orderPrice = 500;
        assertTrue(post.delivery(orderPrice) < dhl.delivery(orderPrice));
    }

    @Test
    void testBothFreeAboveThresholds() {
        Delivery post = new PostDeliveryStrategy();
        Delivery dhl = new DHLDeliveryStrategy();
        
        double orderPrice = 3000;
        assertEquals(0, post.delivery(orderPrice));
        assertEquals(0, dhl.delivery(orderPrice));
    }

    @Test
    void testPostFreeButDHLPaid() {
        Delivery post = new PostDeliveryStrategy();
        Delivery dhl = new DHLDeliveryStrategy();
        
        double orderPrice = 1500;
        assertEquals(0, post.delivery(orderPrice));
        assertEquals(200, dhl.delivery(orderPrice));
    }
}