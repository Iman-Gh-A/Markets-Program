package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.markets.Restaurant;
import ir.ac.kntu.model.classes.markets.ScheduleMarket;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.util.ComparatorHelper;

import java.util.*;

/**
 * MarketService class is a service class between Market class and other classes
 * in this class not existed IO methods
 * @author Iman Ghasemi Arani
 */
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


    /**
     * this method get a Market type and a string for market's name,
     * and then return a list of markets related to thr market type and the string.
     * @param marketType get a Market Type
     * @param marketName get a String for name
     * @return a list of Markets
     */
    public ArrayList<Market> getListOfMarketByType(MarketType marketType, String marketName) {
        ArrayList<Market> relatedMarkets = new ArrayList<>();
        for (Market currentMarket : searchByName(marketName)) {
            if (currentMarket.getMarketType().equals(marketType) && currentMarket.isStatus()) {
                relatedMarkets.add(currentMarket);
            }
        }
        return relatedMarkets;
    }

    /**
     * this method get a string name for market's name and return a list of markets with the name.
     * @param nameSearching just return markets that contain this name
     * @return a list of markets that contain the name
     */
    public ArrayList<Market> searchByName(String nameSearching) {
        ArrayList<Market> marketsContainName = new ArrayList<>();
        for (Market currentMarket : getMarkets()) {
            if (currentMarket.getName().toLowerCase().contains(nameSearching.toLowerCase())) {
                marketsContainName.add(currentMarket);
            }
        }
        return marketsContainName;
    }

    /**
     * this method get a old market and edited market and new status,
     * then update old market whit edited data from edited market and new status.
     * @param oldMarket changing this market's data
     * @param editedMarket change data from this market
     * @param status this is the new status of the market
     */
    public void updateMarket(Market oldMarket, Market editedMarket,boolean status) {
        oldMarket.setName(editedMarket.getName());
        oldMarket.setAddress(editedMarket.getAddress());
        oldMarket.setStatus(status);
        if (oldMarket.getMarketType().equals(MarketType.RESTAURANT)) {
            Restaurant old = (Restaurant) oldMarket;
            Restaurant edited = (Restaurant) editedMarket;
            old.setType(edited.getType());
            old.setWorkHour(edited.getWorkHour());
        } else {
            ScheduleMarket old = (ScheduleMarket) oldMarket;
            ScheduleMarket edited = (ScheduleMarket) editedMarket;
            old.setStartEndSchedule(edited.getStartTime(), edited.getEndTime());
            old.setCapacity(edited.getCapacity());
        }
    }

    /**
     * this method get a product name,
     * search between all markets and put markets that contain the product (for key), and rate of the product in that market (for value) to a hashmap,
     * then entries of hashmap added to a list whit help of linked list class,
     * and then sort the list whit helper class comparator,
     * and the finally added key of enters of hashmap to a list and return it
     * @param marketType get a Market type for return this markets
     * @param productName get a product name
     * @return a list of best markets that contain the product, list is sorted.
     */
    public ArrayList<Market> searchProductByNameBestMarkets(MarketType marketType, String productName) {
        if (productName.equals("")) {
            return getListOfMarketByType(marketType,"");
        }
        ArrayList<Market> marketBestProduct = new ArrayList<>();
        HashMap<Market, Double> bestMarkets = new HashMap<>();
        for (Market currentMarket : getListOfMarketByType(marketType,"")) {
            for (Product currentProduct : currentMarket.getProducts()) {
                if (currentProduct.getName().equalsIgnoreCase(productName)) {
                    bestMarkets.put(currentMarket,currentProduct.getRate());
                }
            }
        }
        List<Map.Entry<Market,Double>> bestMarketsToList = new LinkedList<>(bestMarkets.entrySet());
        bestMarketsToList.sort(ComparatorHelper.rateOfProductDifferentMarketsComparator());
        for (Map.Entry<Market, Double> currentMap : bestMarketsToList) {
            marketBestProduct.add(currentMap.getKey());
        }
        return marketBestProduct;
    }

}
