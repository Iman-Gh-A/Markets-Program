package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.persons.Account;

import java.util.ArrayList;

public class AccountService {
    private final ArrayList<Account> accounts;

    public AccountService() {
        accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return (ArrayList<Account>) accounts.clone();
    }

    public void addAccount(Account newAccount) {
//        if (searchAccountByUsername(newAccount.getUsername()) != null) {
//            throw new IllegalArgumentException("The username has already been used.");
//        }
//        if (searchAccountByID(newAccount.getId()) != null) {
//            throw new IllegalArgumentException("The ID has already been used.");
//        }
        accounts.add(newAccount);
    }

    public Account searchAccountByUsername(String username) {
        for (Account currentAccount: accounts) {
            if (currentAccount.getUsername().equalsIgnoreCase(username)) {
                return currentAccount;
            }
        }
        return null;
    }

    public Account searchAccountByID(String id) {
        for (Account currentAccount: accounts) {
            if (currentAccount.getId().equals(id)) {
                return currentAccount;
            }
        }
        return null;
    }
}
