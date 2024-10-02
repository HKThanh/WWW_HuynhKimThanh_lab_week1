package repositories;

import entities.Account;
import entities.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.Instant;

public class LogRepository {
    private EntityManager em = null;

    public LogRepository() {
        this.em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();
    }

    public void addLog(Account account) {
        Log log = new Log(account.getAccountId(), Instant.now(), Instant.now(), "Login");
        try {
            em.getTransaction().begin();
            em.persist(log);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void updateLog(String accountId) {
        Log log = em.createNamedQuery("Log.findByAccountId", Log.class)
                .setParameter("accountId", accountId)
                .getSingleResult();

        log.setLogoutTime(Instant.now());
        log.setNotes("Logout");

        try {
            em.getTransaction().begin();
            em.merge(log);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
