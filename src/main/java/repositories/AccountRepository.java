package repositories;

import entities.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class AccountRepository {
    private EntityManager em = null;

    public AccountRepository() {
        this.em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();
    }

    public EntityManager getEm() { return em;}

    public Account findAccountByIdAndPassword(String accountId, String password) {
        return em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                .setParameter("accountId", accountId)
                .setParameter("password", password)
                .getSingleResult();
    }
}
