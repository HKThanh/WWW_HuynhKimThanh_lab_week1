package repositories;

import dto.AccountDTO;
import entities.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static EntityManager em = null;

    public AccountRepository() {
        em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();
    }

    public static EntityManager getEm() { return em;}

    public Account findAccountByIdAndPassword(String accountId, String password) {
        return em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                .setParameter("accountId", accountId)
                .setParameter("password", password)
                .getSingleResult();
    }

    public List<AccountDTO> getAllAccountsAndItsRoles(Account account) {
        String jpql = "SELECT a.accountId, a.fullName, a.password FROM Account a Where a.accountId != :accountId";

        List<Object[]> results = em.createQuery(jpql)
                .setParameter("accountId", account.getAccountId())
                .getResultList();

        List<AccountDTO> accounts = new ArrayList<>();

        for (Object[] result : results) {
            accounts.add(new AccountDTO((String) result[0], (String) result[1], (String) result[2], ""));
        }

        return accounts;
    }

    public boolean addAccount(Account account) {
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(Account account) {
        try {
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(String accountId) {
        try {
            Account account = em.find(Account.class, accountId);
            em.getTransaction().begin();
            em.remove(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
