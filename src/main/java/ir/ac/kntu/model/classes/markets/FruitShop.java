package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.enums.MarketType;

public class FruitShop extends Market{
    public FruitShop(String name, String address, MarketType marketType, boolean status) {
        super(name, address, MarketType.FRUITSHOP);
    }
}
