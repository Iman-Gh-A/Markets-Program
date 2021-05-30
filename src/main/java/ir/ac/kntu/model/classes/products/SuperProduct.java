package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

public class SuperProduct extends Product{
    public SuperProduct(String name, Double cost, int availability) {
        super(name, cost, availability, ProductType.SUPERPRODUCT);
    }

    @Override
    public double orderThisProduct(int number) {
        return super.orderThisProduct(number);
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
