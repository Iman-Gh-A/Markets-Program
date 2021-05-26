package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.enums.AvailabilityOfProduct;
import ir.ac.kntu.model.enums.ProductType;

public class Food extends Product {
    public Food(String name, Double cost) {
        super(name, cost, AvailabilityOfProduct.UNLIMITED.toString().toLowerCase(), ProductType.FOOD);
    }
}
