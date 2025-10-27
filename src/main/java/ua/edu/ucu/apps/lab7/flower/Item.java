package ua.edu.ucu.apps.lab7.flower;

public abstract class Item {
    private String description;
    
    public String getDescription () {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public abstract double getPrice();
}
