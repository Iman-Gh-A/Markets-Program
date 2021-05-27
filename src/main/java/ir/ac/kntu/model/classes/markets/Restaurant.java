package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.RestaurantType;

import java.util.Objects;


public class Restaurant extends Market {
    private RestaurantType type;
    private String workHour;
    public Restaurant(String name, String address, RestaurantType type, String workHour) {
        super(name, address, MarketType.RESTAURANT);
        this.type = type;
        this.workHour = workHour;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                " |Restaurant's type: " + getType().toString().toLowerCase() +
                " |Rate: " + String.format("%.1f",getRate()) +
                " |Comment number: " + getComments().size() +
                " |Status: " + isStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return getType() == that.getType() && Objects.equals(getWorkHour(), that.getWorkHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getWorkHour());
    }
}

