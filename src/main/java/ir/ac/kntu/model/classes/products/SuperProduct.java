package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.ProductType;

public class SuperProduct extends Product{
    public SuperProduct(String name, Double cost, String availability) {
        super(name, cost, availability, ProductType.SUPERPRODUCT);
    }

    public void orderThisProduct() {
        setAvailability("" + (Integer.parseInt(getAvailability()) - 1) );
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
