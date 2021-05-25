package ir.ac.kntu.model.classes;


import ir.ac.kntu.model.enums.OrderStatus;

import java.util.Objects;

public class Order {
    private final User user;
    private final Market market;
    private final Product product;
    private final Double cost;
    private final Comment comment;
    private Delivery delivery;
    private OrderStatus status;

    public Order(User user, Market market, Product product, Double cost, Comment comment, OrderStatus status) {
        this.user = user;
        this.market = market;
        this.product = product;
        this.cost = cost;
        this.comment = comment;
        this.status = status;
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

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "Ordering user: " + user.getUsername() +
                "| Market: " + market.getName() +
                "| Product: " + product.getName() +
                "| cost: " + cost +
                "| status=" + status.toString().toLowerCase() +
                (delivery == null ? "" : ("|  delivery=" + delivery.getName())) +
                "| Comment: " + comment.getTextComment() +"(Rate: " + comment.getRate() + ")\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) && Objects.equals(market, order.market) && Objects.equals(product, order.product) && Objects.equals(cost, order.cost) && Objects.equals(delivery, order.delivery) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, market, product, cost, delivery, status);
    }
}
