package ua.edu.ucu.apps.lab7.lab7;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab7.payment.CreditCartPaymentStrategy;
import ua.edu.ucu.apps.lab7.payment.PayPalPaymentStrategy;
import ua.edu.ucu.apps.lab7.payment.Payment;

class PaymentStrategyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    // Credit Card Payment Tests
    @Test
    void testCreditCardPaymentExecutes() {
        Payment payment = new CreditCartPaymentStrategy();
        assertDoesNotThrow(() -> payment.pay(100));
    }

    @Test
    void testCreditCardPaymentOutput() {
        Payment payment = new CreditCartPaymentStrategy();
        payment.pay(100);
        assertTrue(outContent.toString().contains("Paid 100.0 using Credit card"));
    }

    @Test
    void testCreditCardPaymentWithZero() {
        Payment payment = new CreditCartPaymentStrategy();
        payment.pay(0);
        assertTrue(outContent.toString().contains("Paid 0.0 using Credit card"));
    }

    @Test
    void testCreditCardPaymentWithLargeAmount() {
        Payment payment = new CreditCartPaymentStrategy();
        payment.pay(999999.99);
        assertTrue(outContent.toString().contains("Paid 999999.99 using Credit card"));
    }

    // PayPal Payment Tests
    @Test
    void testPayPalPaymentExecutes() {
        Payment payment = new PayPalPaymentStrategy();
        assertDoesNotThrow(() -> payment.pay(100));
    }

    @Test
    void testPayPalPaymentOutput() {
        Payment payment = new PayPalPaymentStrategy();
        payment.pay(100);
        assertTrue(outContent.toString().contains("Paid 100.0 using PayPal"));
    }

    @Test
    void testPayPalPaymentWithZero() {
        Payment payment = new PayPalPaymentStrategy();
        payment.pay(0);
        assertTrue(outContent.toString().contains("Paid 0.0 using PayPal"));
    }

    @Test
    void testPayPalPaymentWithLargeAmount() {
        Payment payment = new PayPalPaymentStrategy();
        payment.pay(999999.99);
        assertTrue(outContent.toString().contains("Paid 999999.99 using PayPal"));
    }

    // Interface Tests
    @Test
    void testCreditCardImplementsPayment() {
        Payment payment = new CreditCartPaymentStrategy();
        assertNotNull(payment);
        assertTrue(payment instanceof Payment);
    }

    @Test
    void testPayPalImplementsPayment() {
        Payment payment = new PayPalPaymentStrategy();
        assertNotNull(payment);
        assertTrue(payment instanceof Payment);
    }

    // Various Amount Tests
    @Test
    void testCreditCardWithDecimalAmount() {
        Payment payment = new CreditCartPaymentStrategy();
        payment.pay(123.45);
        assertTrue(outContent.toString().contains("123.45"));
    }

    @Test
    void testPayPalWithDecimalAmount() {
        Payment payment = new PayPalPaymentStrategy();
        payment.pay(678.90);
        assertTrue(outContent.toString().contains("678.9"));
    }
}