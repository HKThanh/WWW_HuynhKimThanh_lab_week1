package services;

import dto.AccountDTO;
import entities.Account;
import entities.Role;
import repositories.AccountRepository;
import repositories.RoleRepository;

import java.util.List;

public class AccountServices {
    AccountRepository accountRepository = null;

    public AccountServices() {
        accountRepository = new AccountRepository();
    }

    public Account findAccountByIdAndPassword(Object accountIdObj, Object passwordObj) {
        if (accountIdObj == null || passwordObj == null) {
            return null;
        }

        String accountId = accountIdObj.toString();
        String password = passwordObj.toString();
        Account account = null;

        try {
            account = accountRepository.findAccountByIdAndPassword(accountId, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<AccountDTO> getAllAccountAndRoles(Object accountIdObj, Object passwordObj) {
        Account account = findAccountByIdAndPassword(accountIdObj, passwordObj);
        List<AccountDTO> accountAndRoles = accountRepository.getAllAccountsAndItsRoles(account);
        RoleRepository roleRepository = new RoleRepository();

        for (AccountDTO accountDTO : accountAndRoles) {
            Role role = roleRepository.getRoleIDByAccountID(accountDTO.getAccountId());
            if (role == null) {
                accountDTO.setRole("None");
                continue;
            }

            accountDTO.setRole(role.getRoleName());
        }

        return accountAndRoles;
    }
}
