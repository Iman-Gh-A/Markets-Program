package ir.ac.kntu.model.classes;

import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;

public class Account {
    private String id;
    private String name;
    private String username;
    private String password;
    private final AccountType accountType;

    public Account(String id, String name, String username, String password, AccountType accountType) throws IOException {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IOException("The name should be a-z and A-Z");
        }
        if (!id.matches("\\d{10}")) {
            throw new IOException("The ID should be 10 number");
        }
        if (!username.matches("[a-zA-Z0-9_]+")) {
            throw new IOException("The username should be a-z, A-Z, 0-9 and _ character");
        }
        if (!password.matches("[a-zA-Z0-9_]{4,}")) {
            throw new IOException("The password should be a-z, A-Z, 0-9 and _ character which is at least 4 characters.");
        }
        if (accountType == null) {
            throw new IOException("The account's type shouldn't be blank.");
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

    public void setId(String id) throws IOException {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IOException("The name should be a-z and A-Z");
        }
        this.id = id;
    }

    public void setName(String name) throws IOException {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IOException("The name should be a-z and A-Z");
        }
        this.name = name;
    }

    public void setUsername(String username) throws IOException {
        if (!username.matches("[a-zA-Z0-9_]+")) {
            throw new IOException("The username should be a-z, A-Z, 0-9 and _ character");
        }
        this.username = username;
    }

    public void setPassword(String password) throws IOException {
        if (!password.matches("[a-zA-Z0-9_]{4,}")) {
            throw new IOException("The password should be a-z, A-Z, 0-9 and _ character which is at least 4 characters.");
        }
        this.password = password;
    }
}
