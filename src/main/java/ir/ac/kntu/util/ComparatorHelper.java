package ir.ac.kntu.util;

import ir.ac.kntu.model.classes.markets.Market;

import java.util.Comparator;
import java.util.Map;

public class ComparatorHelper {

    private static final Comparator<Map.Entry<Market,Double>> RATE_OF_PRODUCT_DIFFERENT_MARKETS = (MarketIntegerEntry, t1) -> t1.getValue().compareTo(MarketIntegerEntry.getValue());

    private static final Comparator<Map.Entry<String,Integer>> SCHEDULE = Map.Entry.comparingByKey();

    public static Comparator<Map.Entry<Market, Double>> rateOfProductDifferentMarketsComparator() {
        return RATE_OF_PRODUCT_DIFFERENT_MARKETS;
    }

    public static Comparator<Map.Entry<String,Integer>> schedule() {
        return SCHEDULE;
    }

}
