package ua.edu.payment;

public class CreditCartPaymentStrategy implements Payment {

    @Override
    public void pay(double price) {
        System.out.println("Paid " + price + " using Credit card.");
    }

}
