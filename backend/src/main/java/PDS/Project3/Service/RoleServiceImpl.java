package PDS.Project3.Service;

import PDS.Project3.Domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Override
    public Role getRoleByUsername(String username) {
        return null;
    }

    @Override
    public Collection<Role> getRoles() {
        return List.of();
    }
}
