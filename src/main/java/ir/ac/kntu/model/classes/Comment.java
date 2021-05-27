package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.classes.products.Product;

import java.util.ArrayList;
import java.util.Objects;

public class Comment {
    private final String textComment;
    private final User commentingUser;
    private final Integer rate;
    private final Market market;
    private final ArrayList<Product> products;

    public Comment(String textComment, User commentingUser, Integer rate, Market market, ArrayList<Product> products) {
        this.market = market;
        this.products = products;
        this.textComment = textComment;
        this.commentingUser = commentingUser;
        this.rate = rate;

    }

    public String getTextComment() {
        return textComment;
    }

    public Integer getRate() {
        return rate;
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) products.clone();
    }

    @Override
    public String toString() {
        return  "User: " + commentingUser.getUsername() +
                " |Comment: " + textComment +
                "  (rate:)" + rate + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return rate.equals(comment.rate) && Objects.equals(textComment, comment.textComment) && Objects.equals(commentingUser, comment.commentingUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textComment, commentingUser, rate);
    }

    public Market getMarket() {
        return market;
    }
}
