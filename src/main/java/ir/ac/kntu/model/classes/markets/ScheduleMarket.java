package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.enums.MarketType;
import ir.ac.kntu.util.ComparatorHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleMarket extends Market{

    private Integer startTime;
    private Integer endTime;

    public ScheduleMarket(String name, String address,MarketType marketType, Integer startTime, Integer endTime, int capacity) {
        super(name, address, marketType);
        setCapacity(capacity);
        setStartEndSchedule(startTime,endTime);
    }

    public void setStartEndSchedule(Integer startTime, Integer endTime) {
        if (startTime == null || endTime == null ) {
            throw new IllegalArgumentException("Times shouldn't be blank.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        setSchedule(exportSchedule());
    }

    /**
     * this method export the schedule with start time and end time
     * @return a list of schedule
     */
    private ArrayList<Map.Entry<String,Integer>> exportSchedule() {
        HashMap<String,Integer> schedule = new HashMap<>();
        ArrayList<Map.Entry<String,Integer>> listOfSchedule = new ArrayList<>();
        String temp = "" + startTime;
        for (int i = startTime + 1; i <= endTime; i++) {
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
}
