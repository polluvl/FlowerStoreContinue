package ua.edu.ucu.apps.lab7.payment;

public class PayPalPaymentStrategy implements Payment{

    @Override
    public void pay(double price) {
        System.out.println("Paid " + price + " using PayPal.");
    }

}
