package ir.ac.kntu.menu;

import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.enums.AccountType;


public class UserMenu {

    private final Account account;

    public UserMenu(Account account) throws Exception {
        if (!account.getAccountType().equals(AccountType.USER)) {
            throw new Exception("Account is not user");
        }
        this.account = account;
    }

    public void showMenu() {
        System.out.println("user menu " + account.getName() + account.getAccountType());
    }
}
