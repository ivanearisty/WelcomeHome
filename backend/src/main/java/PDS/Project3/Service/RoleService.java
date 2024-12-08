package PDS.Project3.Service;

import PDS.Project3.Domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface RoleService {
    Role getRoleByUsername(String username);
    Collection<Role> getRoles();
}
