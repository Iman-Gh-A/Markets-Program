package ir.ac.kntu.util;

import ir.ac.kntu.model.classes.Food;
import ir.ac.kntu.model.classes.Restaurant;

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

    private static final Comparator<Map.Entry<Restaurant,Double>> RATE_OF_FOOD_DIFFERENT_RESTAURANTS = (restaurantIntegerEntry, t1) -> t1.getValue().compareTo(restaurantIntegerEntry.getValue());

    public static Comparator<Map.Entry<Restaurant, Double>> rateOfFoodDifferentRestaurantsComparator() {
        return RATE_OF_FOOD_DIFFERENT_RESTAURANTS;
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
