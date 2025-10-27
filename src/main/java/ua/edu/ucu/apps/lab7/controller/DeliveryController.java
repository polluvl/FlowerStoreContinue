package ua.edu.ucu.apps.lab7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ucu.apps.lab7.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab7.delivery.Delivery;
import ua.edu.ucu.apps.lab7.delivery.PostDeliveryStrategy;

@RestController
public class DeliveryController {

    @GetMapping("/delivery/post")
    public double postDelivery(@RequestParam double orderPrice) {
        Delivery delivery = new PostDeliveryStrategy();
        return delivery.delivery(orderPrice);
    }

    @GetMapping("/delivery/dhl")
    public double dhlDelivery(@RequestParam double orderPrice) {
        Delivery delivery = new DHLDeliveryStrategy();
        return delivery.delivery(orderPrice);
    }
}
