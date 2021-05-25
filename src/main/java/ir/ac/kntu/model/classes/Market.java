package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.MarketType;

import java.util.ArrayList;

public class Market {
    private String name;
    private String address;
    private Double rate;
    private final MarketType marketType;
    private final ArrayList<Product> products;
    private final ArrayList<Comment> comments;
    private boolean status;

    public Market(String name, String address, MarketType marketType, boolean status) {
        this.name = name;
        this.address = address;
        this.rate = 5.0;
        this.marketType = marketType;
        this.products = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
}
