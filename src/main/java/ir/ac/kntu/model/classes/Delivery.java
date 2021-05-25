package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.OrderStatus;
import ir.ac.kntu.model.enums.SalaryType;
import ir.ac.kntu.model.enums.VehicleType;

import java.util.ArrayList;
import java.util.Objects;

public class Delivery extends Person{
    private VehicleType vehicleType;
    private SalaryType salaryType;
    private Double baseSalary;
    private String weeklySchedule;
    private final ArrayList<Order> orders;
    private Market market1;
    private Market market2;

    public Delivery(String name, String id, String vehicleType,
                    String salaryType, String baseSalary, String weeklySchedule) {
        super(name, id);
        if (!baseSalary.matches("[0-9.]+") || baseSalary.matches("[.]+")) {
            throw new IllegalArgumentException("\tError: The baseSalary should be double or float number.");
        }
        if (weeklySchedule.equals("")) {
            throw new IllegalArgumentException("\tError: The weeklySchedule shouldn't be blank.");
        }
        vehicleType = vehicleType.toUpperCase();
        if ( (!vehicleType.matches("CAR") && !vehicleType.matches("MOTOR") )) {
            throw new IllegalArgumentException("\tError: The vehicle type should be between Car or MOTOR");
        }
        salaryType = salaryType.toUpperCase();
        if ( (!salaryType.matches("HOURLY") && !salaryType.matches("PERORDER") )) {
            throw new IllegalArgumentException("\tError: The salary type should be between HOURLY or PERORDER");
        }
        this.vehicleType = VehicleType.valueOf(vehicleType);
        this.salaryType = SalaryType.valueOf(salaryType);
        this.baseSalary = Double.parseDouble(baseSalary);
        this.weeklySchedule = weeklySchedule.toLowerCase();
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setWeeklySchedule(String weeklySchedule) {
        if (weeklySchedule.equals("")) {
            throw new IllegalArgumentException("\tError: The weeklySchedule shouldn't be blank.");
        }
        this.weeklySchedule = weeklySchedule;
    }

    public void setMarket1(Market market1) {
        this.market2 = market1;
    }

    public void setMarket2(Market market2) {
        this.market2 = market2;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public Double getBaseSalary() {
        return baseSalary;
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
            order.setStatus(OrderStatus.SENDING);
            return true;
        } else if (market2 == null) {
            market2 = order.getMarket();
            order.setDelivery(this);
            addOrder(order);
            order.setStatus(OrderStatus.SENDING);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  "name=" + getName() +
                "  ID=" + getId() +
                "  vehicleType=" + vehicleType +
                "  salaryType=" + salaryType +
                "  salary=" + baseSalary +
                "\n  weeklySchedule=" + weeklySchedule +
                (market1 == null ? " " : ("  restaurant1=" + market1.getName())) +
                (market2 == null ? " " : ("  restaurant2=" + market2.getName())) +  "\n";
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
        return vehicleType == delivery.vehicleType && salaryType == delivery.salaryType && Objects.equals(baseSalary, delivery.baseSalary) && Objects.equals(weeklySchedule, delivery.weeklySchedule) && Objects.equals(market1, delivery.market1) && Objects.equals(market2, delivery.market2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, salaryType, baseSalary, weeklySchedule, market1, market2);
    }
}
