package ir.ac.kntu.engine;

import ir.ac.kntu.service.AccountService;

public class Engine {
    private final AccountService accountService;

    public Engine() {
        accountService = new AccountService();
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
