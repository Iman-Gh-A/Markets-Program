package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.classes.persons.Delivery;
import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.util.ComparatorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FruitShop extends Market{
    private String startTime;
    private String endTime;
    private final ArrayList<Delivery> deliveries;
    public FruitShop(String name, String address, String startTime, String endTime) {
        super(name, address, MarketType.FRUITSHOP);
        this.startTime = startTime;
        this.endTime = endTime;
        setSchedule(exportSchedule());
        deliveries = new ArrayList<>();
    }

    private ArrayList<Map.Entry<String,Integer>> exportSchedule() {
        HashMap<String,Integer> schedule = new HashMap<>();
        ArrayList<Map.Entry<String,Integer>> temp2 = new ArrayList<>();
        int start = Integer.parseInt(startTime);
        int end = Integer.parseInt(endTime);
        String temp = "" + start;
        for (int i = start + 2; i <= end; i+=2) {
            schedule.put(temp + " - " + i,0);
            temp = "" + i;
        }
        temp2.addAll(schedule.entrySet());
        temp2.sort(ComparatorHelper.schedule());
        return temp2;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Delivery> getDeliveries() {
        return (ArrayList<Delivery>) deliveries.clone();
    }
}
