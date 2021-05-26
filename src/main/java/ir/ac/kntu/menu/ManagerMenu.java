package ir.ac.kntu.menu;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.enums.AccountType;

import java.io.IOException;

public class ManagerMenu {
    private final Account account;
    private final Engine engine;

    public ManagerMenu(Account account, Engine engine) throws IOException {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.MANAGER)) {
            throw new IOException("Account is not manager");
        }
        this.account = account;
    }

    public void showMenu() {
        System.out.println("manager menu " + account.getName() + account.getAccountType());
    }
}
