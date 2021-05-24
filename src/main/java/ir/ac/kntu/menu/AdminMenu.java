package ir.ac.kntu.menu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.Account;
import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;

public class AdminMenu {
    private final Account account;
    private final Engine engine;

    public AdminMenu(Account account, Engine engine) throws IOException {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.ADMIN)) {
            throw new IOException("Account is not admin");
        }
        this.account = account;
    }

    public void showMenu() {
        System.out.println("admin menu " + account.getName() + account.getAccountType());
    }
}
