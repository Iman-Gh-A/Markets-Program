package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;
import java.util.ArrayList;

public class Manager extends Account {

    private final ArrayList<Market> markets;
    public Manager(String id, String name, String username, String password, AccountType accountType) throws IOException {
        super(id, name, username, password, accountType);
        markets = new ArrayList<>();
    }

    public ArrayList<Market> getMarkets() {
        return (ArrayList<Market>) markets.clone();
    }
}
