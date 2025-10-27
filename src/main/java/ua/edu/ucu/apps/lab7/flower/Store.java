package ua.edu.ucu.apps.lab7.flower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private List<Flower> flowers;

    public Store() {
        this.flowers = new ArrayList<>();
    }

    public void addFlower(Flower fl) {
        this.flowers.add(fl);
    }

    public List<Flower> searchByColor(FlowerColor color) {
        List<Flower> result = flowers.stream()
                                     .filter(flower -> flower.
                                     getColor() == color)
                                     .collect(Collectors.toList());
        System.out.println(result);
        return result;
    }

    public List<Flower> searchByType(FlowerType type) {
        List<Flower> result = flowers.stream()
                                     .filter(flower -> flower.
                                     getFlowerType() == type)
                                     .collect(Collectors.toList());
        System.out.println(result);
        return result;
    }

}
