package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Role;
import PDS.Project3.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRepository;

    @Override
    public Role getRoleByUsername(String username) {
        return roleRepository.getRoleByUserName(username);
    }

    @Override
    public Role getRoleByRoleID(String roleID) {
        return roleRepository.getRoleByRoleID(roleID);
    }

    @Override
    public Collection<Role> getRoles() {
        return List.of();
    }
}
