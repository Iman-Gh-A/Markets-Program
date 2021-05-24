package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;
import java.util.ArrayList;

public class User extends Account{

    private String address;
    private String phone;
    private final ArrayList<Order> orders;

    public User(String id, String name, String username, String password, AccountType accountType, String address, String phone) throws IOException {
        super(id, name, username, password, accountType);
        if (!phone.matches("09-\\d{3}-\\d{3}-\\d{3}")) {
            throw new IOException("The phone should be 11 number and example form is: 09-123-123-123.");
        }
        if (!address.matches("[a-zA-Z0-9-_\\s]+")) {
            throw new IOException("The address shouldn't be blank.");
        }
        this.address = address;
        this.phone = phone;
        orders = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orders.clone();
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
    }
}
