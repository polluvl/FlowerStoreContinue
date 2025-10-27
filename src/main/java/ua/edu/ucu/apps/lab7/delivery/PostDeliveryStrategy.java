package ua.edu.ucu.apps.lab7.delivery;

public class PostDeliveryStrategy implements Delivery{

    @Override
    public double delivery(double orderPrice) {
        System.out.println("Postal delivery");
        if (orderPrice > 1000){
            return 0;
        }

        return 100;
    }

}
