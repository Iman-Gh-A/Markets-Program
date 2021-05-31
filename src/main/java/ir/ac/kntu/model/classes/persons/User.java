package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.Comment;
import ir.ac.kntu.model.classes.Order;
import ir.ac.kntu.model.enums.AccountType;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Objects;

public class User extends Account {

    private String address;
    private String phone;
    private final ArrayList<Order> orders;
    private final ArrayList<Comment> comments;
    private boolean specialAccount;

    public User(String id, String name, String username, String password, String address, String phone, boolean specialAccount) {
        super(id, name, username, password, AccountType.USER);
        this.specialAccount = specialAccount;
        setPhone(phone);
        setAddress(address);
        orders = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public void setPhone(String phone) {
        if (!phone.matches("09-\\d{3}-\\d{3}-\\d{3}")) {
            throw new IllegalArgumentException("The phone should be 11 number and example form is: 09-123-123-123.");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (!address.matches("[a-zA-Z0-9-_\\s]+")) {
            throw new IllegalArgumentException("The address shouldn't be blank.");
        }
        this.address = address;
    }

    public boolean isSpecialAccount() {
        return specialAccount;
    }

    public void setSpecialAccount(boolean specialAccount) {
        this.specialAccount = specialAccount;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }

    public ArrayList<Comment> getComments() {
        return (ArrayList<Comment>) comments.clone();
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return  "ID: " + getId() +
                " |Name: " + getName() +
                " |Username: " + getUsername() +
                " |Phone num: " + getPhone() +
                " |Address: " + getAddress() +
                " |Number of Order: " + getOrders().size() +
                " |Number od Comment: " + getComments().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getOrders(), user.getOrders()) && Objects.equals(getComments(), user.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getPhone(), getOrders(), getComments());
    }
}
