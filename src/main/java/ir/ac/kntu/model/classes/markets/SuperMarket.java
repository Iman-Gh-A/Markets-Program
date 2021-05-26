package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.MarketType;

public class SuperMarket extends Market {
    public SuperMarket(String name, String address, MarketType marketType, boolean status) {
        super(name, address, MarketType.SUPER);
    }
}
