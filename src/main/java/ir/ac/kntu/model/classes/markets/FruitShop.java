package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.util.ComparatorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FruitShop extends ScheduleMarket{

    public FruitShop(String name, String address, Integer startTime, Integer endTime, int capacity) {
        super(name, address, MarketType.FRUITSHOP,startTime,endTime,capacity);
        setSchedule(exportSchedule());
    }

    /**
     * this method export the schedule with start time and end time
     * @return a list of schedule
     */
    private ArrayList<Map.Entry<String,Integer>> exportSchedule() {
        HashMap<String,Integer> schedule = new HashMap<>();
        ArrayList<Map.Entry<String,Integer>> listOfSchedule = new ArrayList<>();
        String temp = "" + getStartTime();
        for (int i = getStartTime() + 2; i <= getEndTime(); i+=2) {
            schedule.put(temp + " - " + i,0);
            temp = "" + i;
        }
        listOfSchedule.addAll(schedule.entrySet());
        listOfSchedule.sort(ComparatorHelper.schedule());
        return listOfSchedule;
    }
}
