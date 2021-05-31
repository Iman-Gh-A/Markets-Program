package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.AccountType;


public class Manager extends Account {

    private Market market;

    public Manager(String id, String name, String username, String password){
        super(id, name, username, password, AccountType.MANAGER);
        market = null;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

}
