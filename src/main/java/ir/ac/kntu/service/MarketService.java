package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.Market;

import java.util.ArrayList;

public class MarketService {
    private final ArrayList<Market> markets;

    public MarketService() {
        markets = new ArrayList<>();
    }

    public ArrayList<Market> getMarkets() {
        return (ArrayList<Market>) markets.clone();
    }
}
