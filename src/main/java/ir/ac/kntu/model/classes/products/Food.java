package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

public class Food extends Product {
    public Food(String name, Double cost) {
        super(name, cost, -1, ProductType.FOOD);
    }

    @Override
    public double orderThisProduct(int number) {
        return getCost();
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
