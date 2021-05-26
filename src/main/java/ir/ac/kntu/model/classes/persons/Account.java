package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.enums.AccountType;

import java.lang.IllegalArgumentException;

public class Account extends Person {
    private String username;
    private String password;
    private final AccountType accountType;

    public Account(String id, String name, String username, String password, AccountType accountType) {
        super(name,id);
        if (!username.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("The username should be a-z, A-Z, 0-9 and _ character");
        }
        if (!password.matches("[a-zA-Z0-9_]{4,}")) {
            throw new IllegalArgumentException("The password should be a-z, A-Z, 0-9 and _ character which is at least 4 characters.");
        }
        if (accountType == null) {
            throw new IllegalArgumentException("The account's type shouldn't be blank.");
        }
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setUsername(String username) {
        if (!username.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("The username should be a-z, A-Z, 0-9 and _ character");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (!password.matches("[a-zA-Z0-9_]{4,}")) {
            throw new IllegalArgumentException("The password should be a-z, A-Z, 0-9 and _ character which is at least 4 characters.");
        }
        this.password = password;
    }
}
