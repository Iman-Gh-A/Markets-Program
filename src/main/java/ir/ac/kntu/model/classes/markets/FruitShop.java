package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.classes.persons.Delivery;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.util.ComparatorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FruitShop extends Market{
    private Integer startTime;
    private Integer endTime;
    private final ArrayList<Delivery> deliveries;
    private int capacity;

    public FruitShop(String name, String address, Integer startTime, Integer endTime, int capacity) {
        super(name, address, MarketType.FRUITSHOP);
        this.capacity = capacity;
        setStartEndSchedule(startTime,endTime);
        deliveries = new ArrayList<>();
    }

    public void setStartEndSchedule(Integer startTime, Integer endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        setSchedule(exportSchedule());
    }

    private ArrayList<Map.Entry<String,Integer>> exportSchedule() {
        HashMap<String,Integer> schedule = new HashMap<>();
        ArrayList<Map.Entry<String,Integer>> listOfSchedule = new ArrayList<>();
        String temp = "" + startTime;
        for (int i = startTime + 2; i <= endTime; i+=2) {
            schedule.put(temp + " - " + i,0);
            temp = "" + i;
        }
        listOfSchedule.addAll(schedule.entrySet());
        listOfSchedule.sort(ComparatorHelper.schedule());
        return listOfSchedule;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public ArrayList<Delivery> getDeliveries() {
        return (ArrayList<Delivery>) deliveries.clone();
    }

    public int getCapacity() {
        return capacity;
    }
}
