package ua.edu.ucu.apps.lab7.flower;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket extends Item {
    private List<Flower> flowers;


    public FlowerBucket() {
        this.flowers = new ArrayList<>();
    }

    public void addFlowers(Flower flower) {
        flowers.add(flower);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (Flower f : flowers) {
            total += f.getPrice();
        }
        return total;
    }

    public Flower searchFlower(FlowerType type) {
        for (Flower flower_in : flowers) {
            if (flower_in.getFlowerType() == type) {
                return flower_in;
            }
        }
        return null;
    }
}
