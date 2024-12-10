package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Role;

import java.util.Collection;

public interface RoleService {
    public Role getRoleByUsername(String username);
    public Role getRoleByRoleID(String roleID);
    public Collection<Role> getRoles();
}
