package services;

import entities.Role;
import repositories.RoleRepository;

public class RoleServices {
    RoleRepository roleRepository = new RoleRepository();

    public RoleServices() {
    }

    public Role getRoleIDByAccountID(String accountId) {
        Role role = null;

        role = roleRepository.getRoleIDByAccountID(accountId);
        return role;
    }
}
