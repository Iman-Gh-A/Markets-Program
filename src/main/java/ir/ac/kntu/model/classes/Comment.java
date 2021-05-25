package ir.ac.kntu.model.classes;

import java.util.Objects;

public class Comment {
    private final String textComment;
    private final User commentingUser;
    private final Integer rate;
    private final Market market;
    private final Product product;

    public Comment(String textComment, User commentingUser, String rate, Market market, Product product) {
        this.market = market;
        this.product = product;
        if ( !rate.matches("[012345]") ) {
            throw new IllegalArgumentException("\tError: The rate should be 0, 1, 2, 3, 4 or 5.");
        }
        this.textComment = textComment;
        this.commentingUser = commentingUser;
        this.rate = Integer.parseInt(rate);

    }

    public String getTextComment() {
        return textComment;
    }

    public Integer getRate() {
        return rate;
    }

//    @Override
//    public String toString() {
//        return  "Commenting User=" + commentingUser.getUsername() +
//                "  Comment for" +
//                " " + product.getName() +
//                " from " + market.getName() +
//                ": " + textComment +
//                "  rate=" + rate + "\n";
//    }

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

    public Product getProduct() {
        return product;
    }

    public Market getMarket() {
        return market;
    }
}
