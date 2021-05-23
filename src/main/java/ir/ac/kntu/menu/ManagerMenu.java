package ir.ac.kntu.menu;

import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;

public class ManagerMenu {
    private final Account account;

    public ManagerMenu(Account account) throws IOException {
        if (!account.getAccountType().equals(AccountType.MANAGER)) {
            throw new IOException("Account is not manager");
        }
        this.account = account;
    }

    public void showMenu() {
        System.out.println("manager menu " + account.getName() + account.getAccountType());
    }
}
