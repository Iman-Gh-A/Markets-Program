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

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }
}
