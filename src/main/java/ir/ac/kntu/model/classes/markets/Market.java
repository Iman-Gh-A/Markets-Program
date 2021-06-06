package ir.ac.kntu.model.classes.markets;

import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.classes.persons.Delivery;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.MarketType;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Market {
    private String name;
    private String address;
    private Double rate;
    private final MarketType marketType;
    private final ArrayList<Product> products;
    private final ArrayList<Order> orders;
    private final ArrayList<Comment> comments;
    private final ArrayList<Delivery> deliveries;
    private ArrayList<Map.Entry<String,Integer>> schedule;
    private int commentsNum;
    private boolean status;
    private int capacity;

    public Market(String name, String address, MarketType marketType) {
        setName(name);
        setAddress(address);
        this.rate = 5.0;
        this.marketType = marketType;
        products = new ArrayList<>();
        comments = new ArrayList<>();
        orders = new ArrayList<>();
        commentsNum = 0;
        this.status = true;
        deliveries = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        commentsNum++;
        updateRate(comment.getRate());
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void setName(String name) {
        if (!name.matches("[a-zA-Z.\\s]+")) {
            throw new IllegalArgumentException("The name of Market should be a-z and A-Z");
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.matches("")) {
            throw new IllegalArgumentException("The address shouldn't be blank.");
        }
        this.address = address;
    }

    public Double getRate() {
        return Double.parseDouble(String.format("%.1f",rate));
    }

    public void updateRate(int addedRate) {
        rate = (rate * (getComments().size()) + addedRate) / (getComments().size() + 1);
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public boolean isStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setSchedule(ArrayList<Map.Entry<String, Integer>> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Product> searchProductsByName(String nameSearching) {
        ArrayList<Product> productsContainName = new ArrayList<>();
        for (Product currentProduct : getProducts()) {
            if (currentProduct.getName().toLowerCase().contains(nameSearching.toLowerCase())) {
                productsContainName.add(currentProduct);
            }
        }
        return productsContainName;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Map.Entry<String, Integer>> getSchedule() {
        ArrayList<Map.Entry<String, Integer>> availableSchedule = new ArrayList<>();
        for (Map.Entry<String,Integer> current: schedule) {
            if (current.getValue() < capacity) {
                availableSchedule.add(current);
            }
        }
        return availableSchedule;
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }

    public ArrayList<Delivery> getDeliveries() {
        return (ArrayList<Delivery>) deliveries.clone();
    }

    public void updateDelivery(Delivery oldDelivery, Delivery editedDelivery) {
        oldDelivery.setName(editedDelivery.getName());
        oldDelivery.setId(editedDelivery.getId());
        oldDelivery.setVehicleType(editedDelivery.getVehicleType());
    }

    @Override
    public String toString() {
        return "Market{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", marketType=" + marketType +
                ", products=" + products +
                ", comments=" + comments +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Market)) {
            return false;
        }
        Market market = (Market) o;
        return isStatus() == market.isStatus() && Objects.equals(getName(), market.getName()) && Objects.equals(getAddress(), market.getAddress()) && Objects.equals(getRate(), market.getRate()) && getMarketType() == market.getMarketType() && Objects.equals(getProducts(), market.getProducts()) && Objects.equals(getComments(), market.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getRate(), getMarketType(), getProducts(), getComments(), isStatus());
    }
}
