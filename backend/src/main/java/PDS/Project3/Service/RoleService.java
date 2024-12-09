package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Role;

import java.util.Collection;

public interface RoleService {
    Role getRoleByUsername(String username);
    Role getRoleByRoleID(String username);
    Collection<Role> getRoles();
}
