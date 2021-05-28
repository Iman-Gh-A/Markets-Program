package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.Order;

import java.util.ArrayList;

public class OrderService {
    private final ArrayList<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
        newOrder.getUser().addOrder(newOrder);
    }

    public Double calculateCostOfOrder(Order order, int num,int capacity) {
        return order.getCost() + (num >= capacity / 2 ? 1.5 * 5000 : 5000);
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }
}
