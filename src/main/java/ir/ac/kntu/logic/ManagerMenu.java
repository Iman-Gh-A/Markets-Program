package ir.ac.kntu.logic;

import ir.ac.kntu.engine.Engine;
import ir.ac.kntu.model.classes.persons.Account;
import ir.ac.kntu.model.enums.AccountType;

public class ManagerMenu {
    private final Account account;
    private final Engine engine;

    public ManagerMenu(Account account, Engine engine) {
        this.engine = engine;
        if (!account.getAccountType().equals(AccountType.MANAGER)) {
            throw new IllegalArgumentException("Account is not manager");
        }
        this.account = account;
    }

    public void showBaseMenu() {
        System.out.println("manager menu " + account.getName() + account.getAccountType());
    }
}
