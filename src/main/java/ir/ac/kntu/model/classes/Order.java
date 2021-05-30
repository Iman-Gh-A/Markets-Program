package ir.ac.kntu.model.classes;


import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.Delivery;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Product;
import ir.ac.kntu.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private final User user;
    private final Market market;
    private final ArrayList<Product> products;
    private final int[] counter;
    private Double cost;
    private Comment comment;
    private Delivery delivery;
    private OrderStatus status;

    public Order(User user, Market market, ArrayList<Product> products, int[] number) {
        this.user = user;
        this.market = market;
        this.products = products;
        counter = number;
        cost = calculateCost();
        comment = null;
        updateStatus();
    }

    private Double calculateCost() {
        Double cost = 0.0;
        for (int i = 0; i < products.size(); i++) {
            cost += products.get(i).orderThisProduct(counter[i]);
        }
        return cost;
    }

    public void addComment(Comment comment) {
        this.comment = comment;
        user.addComment(comment);
        market.addComment(comment);
        for (Product currentProduct: getProducts()) {
            currentProduct.addComment(comment);
        }
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Market getMarket() {
        return market;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void updateStatus() {
        if (status == null) {
            status = OrderStatus.PROCESSING;
        }else if (status.equals(OrderStatus.PROCESSING)) {
            status = OrderStatus.SENDING;
        } else if (status.equals(OrderStatus.SENDING)) {
            status = OrderStatus.DELIVERED;
        }
    }

    private String returnProducts() {
        String result = "";
        for (int i = 0; i < products.size(); i++) {
            result += products.get(i).getName() + "(" + counter[i] + "), ";
        }
        return result;
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) products.clone();
    }

    @Override
    public String toString() {
        return  "Ordering user: " + user.getUsername() +
                "| Market: " + market.getName() +
                "| Products: " + returnProducts() +
                "| cost: " + cost +
                "| status=" + status.toString().toLowerCase() +
                (delivery == null ? "" : ("|  delivery: " + delivery.getName())) +
                (comment == null ? "" : ("| Comment: " + (comment.getTextComment() +"(Rate: " + comment.getRate() + ")"))) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(user, order.user) && Objects.equals(market, order.market) && Objects.equals(cost, order.cost) && Objects.equals(delivery, order.delivery) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, market, cost, delivery, status);
    }
}
