package ir.ac.kntu.service;

import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.classes.persons.User;
import ir.ac.kntu.model.enums.AccountType;

import java.util.ArrayList;

/**
 * AccountService class is a service class between Account class and other classes
 * in this class not existed IO methods
 * @author Iman Ghasemi Arani
 */
public class AccountService {
    private final ArrayList<Account> accounts;

    public AccountService() {
        accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return (ArrayList<Account>) accounts.clone();
    }

    /**
     * if the username and id of the account are unique, add that to accounts list
     * @param newAccount this account should be add
     */
    public void addAccount(Account newAccount) {
        if (searchAccountByUsername(newAccount.getUsername()) != null) {
            throw new IllegalArgumentException("The username has already been used.");
        }
        if (searchAccountByID(newAccount.getId()) != null) {
            throw new IllegalArgumentException("The ID has already been used.");
        }
        accounts.add(newAccount);
    }


    /**
     * this method search and find the unique account in all defined accounts by username
     * @param username search by this username
     * @return if find account, return that else return null
     */
    public Account searchAccountByUsername(String username) {
        for (Account currentAccount: accounts) {
            if (currentAccount.getUsername().equalsIgnoreCase(username)) {
                return currentAccount;
            }
        }
        return null;
    }

    /**
     * this method search and find the unique account in all defined accounts by id
     * @param id search by this id
     * @return if find account, return that else return null
     */
    public Account searchAccountByID(String id) {
        for (Account currentAccount: accounts) {
            if (currentAccount.getId().equals(id)) {
                return currentAccount;
            }
        }
        return null;
    }

    /**
     * this method get a old user and edited user and new password,
     * then update old user whit edited data from edited user and new password.
     * @param oldAccount changing this market's data
     * @param editedAccount change data from this market
     * @param newPassword this is new password if it is filled
     */
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

    /**
     * this method just for manager and admin
     * this method get a old account and edited account and new password,
     * then update old account whit edited data from edited account and new password.
     * @param oldAccount changing this market's data
     * @param editedAccount change data from this market
     * @param newPassword this is new password if it is filled
     */
    public void updateManagerOrAdmin(Account oldAccount, Account editedAccount, String newPassword) {
        if (oldAccount.getAccountType().equals(AccountType.USER) || editedAccount.getAccountType().equals(AccountType.USER)) {
            throw new IllegalArgumentException("the account isn't manager or admin.");
        }
        if (searchAccountByID(editedAccount.getId()) != oldAccount && searchAccountByID(editedAccount.getId()) != null) {
            throw new IllegalArgumentException("The ID has already been used.");
        }
        if (searchAccountByUsername(editedAccount.getUsername()) != oldAccount && searchAccountByUsername(editedAccount.getUsername()) != null) {
            throw new IllegalArgumentException("The username has already been used.");
        }
        oldAccount.setName(editedAccount.getName());
        oldAccount.setId(editedAccount.getId());
        oldAccount.setUsername(editedAccount.getUsername());
        if (!newPassword.equals("")) {
            oldAccount.setPassword(newPassword);
        }
    }

    /**
     * this method get a account type and return list of account related that.
     * @param accountType get a Account Type for return list of Accounts with that.
     * @return a list of accounts
     */
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
