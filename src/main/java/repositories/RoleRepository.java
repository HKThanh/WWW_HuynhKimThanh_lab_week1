package repositories;

import entities.Role;
import jakarta.persistence.EntityManager;

public class RoleRepository {
    private EntityManager em = null;

    public RoleRepository() {
        this.em = AccountRepository.getEm();
    }

    public Role getRoleIDByAccountID(String accountId) {
        String jpql = "SELECT r FROM Role r JOIN GrantAccess ga " +
                "ON r.roleId = ga.roleId JOIN Account a " +
                "ON ga.accountId = a.accountId WHERE a.accountId = :accountId";

        return (Role) em.createQuery(jpql).setParameter("accountId", accountId).getSingleResult();
    }

    public boolean addRoleToAccount(String accountId, String roleId) {
        try {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO grant_access (account_id, role_id) VALUES (?, ?)")
                    .setParameter(1, accountId)
                    .setParameter(2, roleId)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeRoleFromAccount(String accountId, String roleId) {
        try {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM grant_access WHERE account_id = ? AND role_id = ?")
                    .setParameter(1, accountId)
                    .setParameter(2, roleId)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoleForAccount(String accountId, String roleId) {
        try {
            em.getTransaction().begin();
            em.createNativeQuery("UPDATE grant_access SET role_id = ? WHERE account_id = ?")
                    .setParameter(1, roleId)
                    .setParameter(2, accountId)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
