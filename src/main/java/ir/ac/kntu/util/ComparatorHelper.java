package ir.ac.kntu.util;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.products.Food;
import ir.ac.kntu.model.classes.markets.Restaurant;

import java.util.Comparator;
import java.util.Map;

public class ComparatorHelper {

    private static final Comparator<Restaurant> RESTAURANT_RATE = Comparator.comparing(Restaurant::getRate);

    private static final Comparator<Restaurant> RESTAURANT_COMMENT_NUMBER = (restaurant, t1) -> {
        Integer commentNum = restaurant.getComments().size();
        Integer commentNum1 = t1.getComments().size();
        return commentNum.compareTo(commentNum1);
    };

    private static final Comparator<Restaurant> RESTAURANT_STATUS = (restaurant, t1) -> {
        if (restaurant.isStatus()) {
            return -1;
        } else if (t1.isStatus()) {
            return 1;
        }
        return 0;
    };

    private static final Comparator<Food> FOOD_RATE = Comparator.comparing(Food::getRate);

    private static final Comparator<Food> FOOD_COMMENT_NUMBER = (food, t1) -> {
        Integer commentNum = food.getComments().size();
        Integer commentNum1 = t1.getComments().size();
        return commentNum.compareTo(commentNum1);
    };

    private static final Comparator<Map.Entry<Market,Double>> RATE_OF_PRODUCT_DIFFERENT_MARKETS = (MarketIntegerEntry, t1) -> t1.getValue().compareTo(MarketIntegerEntry.getValue());

    private static final Comparator<Map.Entry<String,Integer>> SCHEDULE = Comparator.comparing(Map.Entry::getKey);

    public static Comparator<Map.Entry<Market, Double>> rateOfProductDifferentMarketsComparator() {
        return RATE_OF_PRODUCT_DIFFERENT_MARKETS;
    }

    public static Comparator<Map.Entry<String,Integer>> schedule() {
        return SCHEDULE;
    }

    public static Comparator<Restaurant> rateRestaurantComparator() {
        return RESTAURANT_RATE;
    }

    public static Comparator<Restaurant> commentNumberRestaurantComparator() {
        return RESTAURANT_COMMENT_NUMBER;
    }

    public static Comparator<Restaurant> statusRestaurantComparator() {
        return RESTAURANT_STATUS;
    }

    public static Comparator<Food> rateFoodComparator() {
        return FOOD_RATE;
    }

    public static Comparator<Food> commentNumberFoodComparator() {
        return FOOD_COMMENT_NUMBER;
    }

}
