package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

import java.util.concurrent.RecursiveTask;

public class SuperProduct extends Product{
    public SuperProduct(String name, Double cost, int availability) {
        super(name, cost, availability, ProductType.SUPERPRODUCT);
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
