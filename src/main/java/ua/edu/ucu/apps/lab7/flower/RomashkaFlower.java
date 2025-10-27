package ua.edu.ucu.apps.lab7.flower;

public class RomashkaFlower extends Item{
    private Item item;

    public RomashkaFlower(Item item) {
        this.item = item;
    }
    @Override
    public double getPrice() {
        return item.getPrice();
    }
}
