package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.model.enums.RestaurantType;


public class Restaurant extends Market{
    private RestaurantType type;
    private String workHour;
    public Restaurant(String name, String address, MarketType marketType, boolean status, RestaurantType type, String workHour) {
        super(name, address, MarketType.RESTAURANT, status);
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

}

