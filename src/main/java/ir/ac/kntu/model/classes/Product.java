package ir.ac.kntu.model.classes;

import java.util.ArrayList;

public class Product {
    private final String name;
    private final Double cost;
    private Double rate;
    private final ArrayList<Comment> comments;

    public Product(String name, Double cost) {
        this.name = name;
        this.cost = cost;
        this.rate = 5.0;
        comments = new ArrayList<>();
    }

    public Double getCost() {
        return cost;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getName() {
        return name;
    }
}
