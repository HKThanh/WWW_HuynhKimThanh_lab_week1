package services;

import entities.Account;
import repositories.AccountRepository;

public class AccountServices {
    public AccountServices() {
    }

    public Account findAccountByIdAndPassword(String accountId, String password) {
        return new AccountRepository().findAccountByIdAndPassword(accountId, password);
    }
}
