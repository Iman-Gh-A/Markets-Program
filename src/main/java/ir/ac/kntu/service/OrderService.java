package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.Order;

import java.util.ArrayList;

public class OrderService {
    private final ArrayList<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }
}
