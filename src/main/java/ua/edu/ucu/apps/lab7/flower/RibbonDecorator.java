package ua.edu.ucu.apps.lab7.flower;

public class RibbonDecorator extends ItemDecorator {
    private Item item;

    public RibbonDecorator (Item item) {
        this.item = item;
    }
    @Override
    public double getPrice() {
        return this.item.getPrice() + 40;
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " with ribbon.";
    }
    
}
