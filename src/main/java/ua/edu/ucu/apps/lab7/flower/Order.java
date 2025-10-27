package ua.edu.ucu.apps.lab7.flower;
import java.util.LinkedList;
import java.util.List;

import ua.edu.payment.Payment;
import ua.edu.ucu.apps.lab7.delivery.Delivery;

public class Order {
    private List<Item> items = new LinkedList<>();

    private Payment payment;
    private Delivery delivery;

    public Payment getPayment() {
        return this.payment;
    }
    
    public void setPaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public void setDeliveryStrategy(Delivery delivery) {
        this.delivery = delivery;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void processOrder() {
        double totalPrice = calculateTotalPrice();
        payment.pay(totalPrice);
        delivery.delivery(totalPrice);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> items() {
        return items;
    }

}
