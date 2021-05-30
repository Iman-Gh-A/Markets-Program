package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.AccountType;

import java.util.ArrayList;

public class Manager extends Account {

    private final ArrayList<Market> markets;
    public Manager(String id, String name, String username, String password){
        super(id, name, username, password, AccountType.MANAGER);
        markets = new ArrayList<>();
    }

    public ArrayList<Market> getMarkets() {
        return (ArrayList<Market>) markets.clone();
    }
}
