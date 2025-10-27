package ua.edu.ucu.apps.lab7.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import ua.edu.payment.CreditCartPaymentStrategy;
import ua.edu.payment.PayPalPaymentStrategy;
import ua.edu.payment.Payment;

public class PaymentController {

    @GetMapping("/payments")
    public List<String> getPayments() {
        double price = 1200;

        Payment creditCardPayment = new CreditCartPaymentStrategy();
        Payment payPalPayment = new PayPalPaymentStrategy();

        creditCardPayment.pay(price);
        payPalPayment.pay(price);

        return List.of(
            "Credit Card Payment processed for " + price + " UAH",
            "PayPal Payment processed for " + price + " UAH"
        );
    }
}
