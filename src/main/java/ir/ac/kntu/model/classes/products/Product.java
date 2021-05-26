package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.enums.ProductType;

import java.util.ArrayList;

public class Product {
    private final String name;
    private final Double cost;
    private String availability;
    private Double rate;
    private final ArrayList<Comment> comments;
    private int commentsNum;
    private final ProductType productType;

    public Product(String name, Double cost, String availability, ProductType productType) {
        this.name = name;
        this.cost = cost;
        this.availability = availability;
        this.productType = productType;
        this.rate = 5.0;
        comments = new ArrayList<>();
        commentsNum = 0;

    }

    public void addComment(Comment comment) {
        comments.add(comment);
        commentsNum++;
        rate = (comment.getRate() + rate * getComments().size() ) / (getComments().size() + 1);
    }

    public Double getCost() {
        return cost;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getName() {
        return name;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public String getAvailability() {
        return availability;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
