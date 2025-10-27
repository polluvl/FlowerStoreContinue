package ua.edu.ucu.apps.lab7.flower;

public class Flower {

    private FlowerColor color;
    private int sepalLength;
    private int price;
    private FlowerType flowerType;

    public Flower(FlowerColor newColor, FlowerType newflowerType,
    int sepalLength, int price) {
        this.color = newColor;
        this.flowerType = newflowerType;
        this.sepalLength = sepalLength;
        this.price = price;
    }

    public FlowerColor getColor() {
        return this.color;
    }

    public void setColor(FlowerColor newcolor) {
        this.color = newcolor;
    }

    public FlowerType getFlowerType() {
        return this.flowerType;
    }

    public void setFlowerType(FlowerType newflowerType) {
        this.flowerType = newflowerType;
    }

    public int getSepalLength() {
        return this.sepalLength;
    }

    public void setSepalLength(int newsepalLength) {
        this.sepalLength = newsepalLength;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int newprice) {
        this.price = newprice;
    }
}
