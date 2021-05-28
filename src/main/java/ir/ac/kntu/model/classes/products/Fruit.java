package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

public class Fruit extends Product {
    public Fruit(String name, Double cost, int number) {
        super(name, cost, number, ProductType.FRUIT);
    }
}
