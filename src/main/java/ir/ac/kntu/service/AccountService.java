package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.Manager;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.enums.AccountType;
import ir.ac.kntu.model.enums.MarketType;

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
        if (searchAccountByUsername(newAccount.getUsername()) != null) {
            throw new IllegalArgumentException("The username has already been used.");
        }
        if (searchAccountByID(newAccount.getId()) != null) {
            throw new IllegalArgumentException("The ID has already been used.");
        }
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

    public void updateUser(Account oldAccount,Account editedAccount,String newPassword) {
        if (!oldAccount.getAccountType().equals(AccountType.USER) || !editedAccount.getAccountType().equals(AccountType.USER)) {
            throw new IllegalArgumentException("the account isn't user.");
        }
        User oldUser = (User) oldAccount;
        User editedUser = (User) editedAccount;
        if (searchAccountByID(editedUser.getId()) != oldUser && searchAccountByID(editedUser.getId()) != null) {
            throw new IllegalArgumentException("The ID has already been used.");
        }
        if (searchAccountByUsername(editedUser.getUsername()) != oldUser && searchAccountByUsername(editedUser.getUsername()) != null) {
            throw new IllegalArgumentException("The username has already been used.");
        }
        oldUser.setName(editedUser.getName());
        oldUser.setId(editedUser.getId());
        oldUser.setUsername(editedUser.getUsername());
        if (!newPassword.equals("")) {
            oldUser.setPassword(newPassword);
        }
        oldUser.setPhone(editedUser.getPhone());
        oldUser.setAddress(editedUser.getAddress());
    }

    public void updateManager(Account oldAccount,Account editedAccount,String newPassword) {
        if (!oldAccount.getAccountType().equals(AccountType.MANAGER) || !editedAccount.getAccountType().equals(AccountType.MANAGER)) {
            throw new IllegalArgumentException("the account isn't manager.");
        }
        Manager oldManager = (Manager) oldAccount;
        Manager editedManager = (Manager) editedAccount;
        if (searchAccountByID(editedManager.getId()) != oldManager && searchAccountByID(editedManager.getId()) != null) {
            throw new IllegalArgumentException("The ID has already been used.");
        }
        if (searchAccountByUsername(editedManager.getUsername()) != oldManager && searchAccountByUsername(editedManager.getUsername()) != null) {
            throw new IllegalArgumentException("The username has already been used.");
        }
        oldManager.setName(editedManager.getName());
        oldManager.setId(editedManager.getId());
        oldManager.setUsername(editedManager.getUsername());
        if (!newPassword.equals("")) {
            oldManager.setPassword(newPassword);
        }
    }

    public ArrayList<Account> getListOfAccountByType(AccountType accountType) {
        ArrayList<Account> relatedAccounts = new ArrayList<>();
        for (Account currentAccount : getAccounts()) {
            if (currentAccount.getAccountType().equals(accountType)) {
                relatedAccounts.add(currentAccount);
            }
        }
        return relatedAccounts;
    }
}
