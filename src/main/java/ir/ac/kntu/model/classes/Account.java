package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;

public class Account {
    private final String id;
    private final String name;
    private final String username;
    private final String password;
    private final AccountType accountType;

    public Account(String id, String name, String username, String password, AccountType accountType) throws IOException {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IOException("the name should be a-z and A-Z");
        }
        if (!id.matches("\\d{10}")) {
            throw new IOException("the ID should be 10 number");
        }
        if (!username.matches("[a-zA-Z0-9_]+")) {
            throw new IOException("the username should be a-z, A-Z, 0-9 and _ character");
        }
        if (!password.matches("[a-zA-Z0-9_]{4,}")) {
            throw new IOException("the password should be a-z, A-Z, 0-9 and _ character which is at least 4 characters.");
        }
        if (accountType == null) {
            throw new IOException("the account's type shouldn't be blank.");
        }
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
