package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.persons.Delivery;

import java.util.ArrayList;

public class DeliveryService {
    private final ArrayList<Delivery> deliveries;

    public DeliveryService() {
        deliveries = new ArrayList<>();
    }

    public ArrayList<Delivery> getDeliveries() {
        return (ArrayList<Delivery>) deliveries.clone();
    }
}
