package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.enums.VehicleType;

import java.util.ArrayList;
import java.util.Objects;

public class Delivery extends Person {

    private VehicleType vehicleType;
    private final ArrayList<Order> orders;

    public Delivery(String name, String id, VehicleType vehicleType) {
        super(name, id);
        this.vehicleType = vehicleType;
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return  "name=" + getName() +
                "  ID=" + getId() +
                "  vehicleType=" + vehicleType + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delivery)) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return getVehicleType() == delivery.getVehicleType() && Objects.equals(getOrders(), delivery.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicleType(), getOrders());
    }
}
