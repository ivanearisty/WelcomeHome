package PDS.Project3.Repository;

import PDS.Project3.Domain.Role;
import PDS.Project3.Domain.RowMapper.RowMapperRole;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static PDS.Project3.Queries.Queries.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public Role get(String id) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Boolean delete(String roleId) {
        return null;
    }

    @Override
    public void addRoleToUser(String roleId, String userName) {
        log.info("Add role {} to user {}", roleId, userName);
        try{
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME, Map.of("roleID", roleId), new RowMapperRole());
            jdbc.update(INSERT_ROLE_TO_USER, Map.of("userName", userName, "roleID", Objects.requireNonNull(role).getRoleID()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public Role getRoleByUserName(String userName) {
        log.info("Get role by name {}", userName);
        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_FROM_USERNAME, Map.of("userName", userName), new RowMapperRole());
            log.info("Found role: {} for user: {}", role, userName);
            return role;
        } catch (Exception e) {
            log.error("Error fetching role for user: {}", userName, e);
            throw new RoleNotFoundException();
        }
    }

    @Override
    public void updateUserRole(String roleId, String userName) {

    }

    @SneakyThrows
    @Override
    public Role getRoleByRoleID(String roleId) {
        log.info("Fetching Role {}", roleId);
        try{
            return jdbc.queryForObject(SELECT_ROLE_BY_NAME, Map.of("roleID", roleId), new RowMapperRole());
        }
        catch (Exception e){
            log.error("Error fetching role: {}", roleId);
            e.printStackTrace();
            throw new RoleNotFoundException();
        }
    }
}
