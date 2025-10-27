package ua.edu.ucu.apps.lab7.flower;

public class BasketDecorator extends ItemDecorator{
    private Item item;

    public BasketDecorator (Item item) {
        this.item = item;
    }

    public double getPrice() {
        return this.item.getPrice() + 4;
    }

    @Override
    public String getDescription() {
        return this.getDescription();
    }


}
