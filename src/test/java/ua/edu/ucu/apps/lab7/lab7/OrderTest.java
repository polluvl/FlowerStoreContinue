package ua.edu.ucu.apps.lab7.lab7;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab7.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab7.delivery.PostDeliveryStrategy;
import ua.edu.ucu.apps.lab7.flower.Flower;
import ua.edu.ucu.apps.lab7.flower.FlowerBucket;
import ua.edu.ucu.apps.lab7.flower.FlowerColor;
import ua.edu.ucu.apps.lab7.flower.FlowerType;
import ua.edu.ucu.apps.lab7.flower.Order;
import ua.edu.ucu.apps.lab7.payment.CreditCartPaymentStrategy;
import ua.edu.ucu.apps.lab7.payment.PayPalPaymentStrategy;

class OrderTest {

    private Order order;
    private FlowerBucket bucket;
    private Flower flower;

    @BeforeEach
    void setUp() {
        order = new Order();
        bucket = new FlowerBucket();
        flower = new Flower(FlowerColor.RED, FlowerType.ROSE, 50, 100);
    }

    @Test
    void testAddItem() {
        order.addItem(bucket);
        assertEquals(1, order.items().size());
    }

    @Test
    void testRemoveItem() {
        order.addItem(bucket);
        order.removeItem(bucket);
        assertEquals(0, order.items().size());
    }

    @Test
    void testCalculateTotalPrice() {
        bucket.addFlowers(flower);
        order.addItem(bucket);
        assertEquals(100, order.calculateTotalPrice());
    }

    @Test
    void testCalculateTotalPriceMultipleItems() {
        FlowerBucket bucket2 = new FlowerBucket();
        Flower flower2 = new Flower(FlowerColor.BLUE, FlowerType.TULIP, 40, 80);
        
        bucket.addFlowers(flower);
        bucket2.addFlowers(flower2);
        
        order.addItem(bucket);
        order.addItem(bucket2);
        
        assertEquals(180, order.calculateTotalPrice());
    }

    @Test
    void testSetPaymentStrategy() {
        CreditCartPaymentStrategy payment = new CreditCartPaymentStrategy();
        order.setPaymentStrategy(payment);
        assertEquals(payment, order.getPayment());
    }

    @Test
    void testSetDeliveryStrategy() {
        PostDeliveryStrategy delivery = new PostDeliveryStrategy();
        order.setDeliveryStrategy(delivery);
        assertEquals(delivery, order.getDelivery());
    }

    @Test
    void testProcessOrderWithPostDelivery() {
        bucket.addFlowers(flower);
        order.addItem(bucket);
        order.setPaymentStrategy(new CreditCartPaymentStrategy());
        order.setDeliveryStrategy(new PostDeliveryStrategy());
        
        assertDoesNotThrow(() -> order.processOrder());
    }

    @Test
    void testProcessOrderWithDHLDelivery() {
        bucket.addFlowers(flower);
        order.addItem(bucket);
        order.setPaymentStrategy(new PayPalPaymentStrategy());
        order.setDeliveryStrategy(new DHLDeliveryStrategy());
        
        assertDoesNotThrow(() -> order.processOrder());
    }

    @Test
    void testEmptyOrder() {
        assertEquals(0, order.calculateTotalPrice());
    }

    @Test
    void testItemsList() {
        order.addItem(bucket);
        assertNotNull(order.items());
        assertTrue(order.items().contains(bucket));
    }
}