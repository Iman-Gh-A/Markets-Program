package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.enums.MarketType;

import java.util.ArrayList;

/**
 * OrderService class is a service class between Order class and other classes
 * in this class not existed IO methods
 * @author Iman Ghasemi Arani
 */
public class OrderService {
    private final ArrayList<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    /**
     * add new Order to list of orders.
     * in this progress added new order to list of All orders,
     * then add that to list of order history for ordering user,
     * and finally add that to order history for ordering market.
     * @param newOrder adding this new order
     */
    public void addOrder(Order newOrder) {
        orders.add(newOrder);
        newOrder.getUser().addOrder(newOrder);
        newOrder.getMarket().addOrder(newOrder);
    }

    /**
     * this class get a order and get num,
     * and calculate the finally cost of order whit this delivery
     * @param order the calculated cost is about this order
     * @param num get a int number
     * @return a double number for finally cost
     */
    public Double calculateCostOfOrder(Order order, int num) {
        if (order.getUser().isSpecialAccount() && order.getMarket().getMarketType().equals(MarketType.SUPER)) {
            return order.getCost();
        }
        return order.getCost() + (num >= order.getMarket().getCapacity() / 2 ? 1.5 * 5000 : 5000);
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }
}
