package ua.edu.ucu.apps.lab7.flower;

public class PaperDecorator extends ItemDecorator{
    private Item item;

    public PaperDecorator(Item item) {
        this.item = item;
    }
    @Override
    public double getPrice() {
        return this.item.getPrice() + 13;
    }

    @Override
    public String getDescription() {
        return this.getDescription();
    }

}
