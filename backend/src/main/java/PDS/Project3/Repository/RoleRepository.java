package PDS.Project3.Repository;

import PDS.Project3.Domain.Role;

import java.util.Collection;

public interface RoleRepository<T extends Role> {

    T create(T role);
    Collection<T> list(int page, int pageSize);
    T get(String id);
    T update(T role);
    Boolean delete(String roleId);

    void addRoleToUser(String roleId, String userName);
    Role getRoleByUserEmail(String userEmail);
    Role getRoleByUserName(String userName);
    void updateUserRole(String roleId, String userName);
}
