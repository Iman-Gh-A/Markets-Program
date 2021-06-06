package ir.ac.kntu.model.classes.products;

import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.enums.AvailabilityOfProduct;
import ir.ac.kntu.model.enums.ProductType;

import java.util.ArrayList;

public class Product {
    private final String name;
    private final Double cost;
    private int availability;
    private Double rate;
    private final ArrayList<Comment> comments;
    private int commentsNum;
    private final ProductType productType;

    public Product(String name, Double cost, int availability, ProductType productType) {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("The name of Product should be a-z and A-Z ");
        }
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
        updateRate(comment.getRate());
    }

    public Double getCost() {
        return cost;
    }

    public Double getRate() {
        return rate;
    }

    public void updateRate(int addedRate) {
        rate = (addedRate + rate * getComments().size() ) / (getComments().size() + 1);
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

    public int getAvailabilityInt() {
        return availability;
    }

    public String getAvailability() {
        if (availability < 0) {
            return AvailabilityOfProduct.UNLIMITED.toString().toLowerCase();
        }
        if (availability == 0) {
            return AvailabilityOfProduct.ENDED.toString().toLowerCase();
        }
        return "" + availability;
    }

    public double orderThisProduct(int number) {
        setAvailability(getAvailabilityInt() - number );
        return getCost() * number;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
