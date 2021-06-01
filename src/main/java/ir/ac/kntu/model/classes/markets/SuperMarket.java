package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.enums.MarketType;


public class SuperMarket extends ScheduleMarket{

    public SuperMarket(String name, String address, Integer startTime, Integer endTime, int capacity) {
        super(name, address, MarketType.SUPER,startTime,endTime,capacity);
    }

}
