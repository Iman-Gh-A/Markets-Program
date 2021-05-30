package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.OrderStatus;

import java.util.Objects;

public class DeliveryForRestaurant extends Delivery{

    private String weeklySchedule;
    private Market market1;
    private Market market2;

    public DeliveryForRestaurant(String name, String id, String vehicleType, String weeklySchedule) {
        super(name, id, vehicleType);
        setWeeklySchedule(weeklySchedule);
    }

    public void setWeeklySchedule(String weeklySchedule) {
        if (weeklySchedule.equals("")) {
            throw new IllegalArgumentException("\tError: The weeklySchedule shouldn't be blank.");
        }
        this.weeklySchedule = weeklySchedule.toLowerCase();
    }

    public void setMarket1(Market market1) {
        this.market2 = market1;
    }

    public void setMarket2(Market market2) {
        this.market2 = market2;
    }

    public String getWeeklySchedule() {
        return weeklySchedule;
    }

    public Market getMarket1() {
        return market1;
    }

    public Market getMarket2() {
        return market2;
    }

    /**
     * gettingOrder<p>
     * if restaurant 1 is empty set it for order and return true, else if restaurant 2 is empty set it and return true, else return false.
     * @param order getting this order whit the delivery
     * @return a boolean that showed successful or not.
     */
    public Boolean gettingOrder(Order order) {
        if (market1 == null) {
            market1 = order.getMarket();
            order.setDelivery(this);
            addOrder(order);
            order.updateStatus();
            return true;
        } else if (market2 == null) {
            market2 = order.getMarket();
            order.setDelivery(this);
            addOrder(order);
            order.updateStatus();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  "name=" + getName() +
                "  ID=" + getId() +
                "  vehicleType=" + getVehicleType() +
                "\n  weeklySchedule=" + weeklySchedule +
                (market1 == null ? " " : ("  restaurant1=" + market1.getName())) +
                (market2 == null ? " " : ("  restaurant2=" + market2.getName())) +  "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryForRestaurant)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DeliveryForRestaurant that = (DeliveryForRestaurant) o;
        return Objects.equals(getWeeklySchedule(), that.getWeeklySchedule()) && Objects.equals(getMarket1(), that.getMarket1()) && Objects.equals(getMarket2(), that.getMarket2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeeklySchedule(), getMarket1(), getMarket2());
    }
}
