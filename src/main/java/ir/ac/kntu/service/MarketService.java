package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.MarketType;

import java.util.ArrayList;

public class MarketService {
    private final ArrayList<Market> markets;

    public MarketService() {
        markets = new ArrayList<>();
    }

    public void addMarket(Market market) {
        markets.add(market);
    }

    public ArrayList<Market> getMarkets() {
        return (ArrayList<Market>) markets.clone();
    }

    public ArrayList<Market> getListOfMarketByType(MarketType marketType, String marketName) {
        ArrayList<Market> relatedMarkets = new ArrayList<>();
        for (Market currentMarket : searchByName(marketName)) {
            if (currentMarket.getMarketType().equals(marketType)) {
                relatedMarkets.add(currentMarket);
            }
        }
        return relatedMarkets;
    }

    public ArrayList<Market> searchByName(String nameSearching) {
        ArrayList<Market> marketsContainName = new ArrayList<>();
        for (Market currentMarket : getMarkets()) {
            if (currentMarket.getName().toLowerCase().contains(nameSearching.toLowerCase())) {
                marketsContainName.add(currentMarket);
            }
        }
        return marketsContainName;
    }

}
