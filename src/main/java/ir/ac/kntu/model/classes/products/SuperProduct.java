package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

public class SuperProduct extends Product{
    public SuperProduct(String name, Double cost, String number) {
        super(name, cost, number, ProductType.SUPERPRODUCT);
    }
}
