package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Role;
import PDS.Project3.Domain.RowMapper.RowMapperUser;
import PDS.Project3.Domain.Entities.User;
import PDS.Project3.Domain.UserPrincipal;
import PDS.Project3.Queries.Queries;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static PDS.Project3.Domain.Enum.Roles.ROLE_ADMIN;
import static PDS.Project3.Queries.Queries.COUNT_USERNAME;
import static PDS.Project3.Queries.Queries.INSERT_USER;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository<Role> roleRepository;

    @Override
    public User create(User user) {
        //TODO: Check if email is unique
        try{
            if(user.getUserName().isEmpty()){
                user.setUserName(user.getEmail().substring(0,user.getEmail().indexOf('@')));
            }
            if(this.countUsernames(user) > 0){
                throw new RuntimeException("Username already exists");
            }
            user.setEnabled(true);
            user.setNonLocked(true);
            SqlParameterSource parameterSource = getSQLParameterSource(user);
            jdbc.update(INSERT_USER,parameterSource);
            roleRepository.addRoleToUser(ROLE_ADMIN.name(), user.getUserName()); //TODO: MAKE THIS CLIENT FOR LATER
            log.info("User created: {}", user);
            log.info("With parameters: {}", parameterSource.toString());
            return user;
        }catch (EmptyResultDataAccessException exception){
            log.info("User creation failed: {}", user);
        }catch (Exception exception){
            log.info("Unknown error when creating a new user: {}", user);
            exception.printStackTrace();
        }
        return null;
    }

    private Integer countUsernames(User user){
        return jdbc.queryForObject(COUNT_USERNAME, Map.of("userName", user.getUserName()), Integer.class);
    }

    private SqlParameterSource getSQLParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("password", encoder.encode(user.getPassword()))
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("enabled", user.isEnabled())
                .addValue("nonLocked", user.isNonLocked());
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return  null;
    }

    @Override
    public User getUserByUsername(String id) {
        User user = jdbc.queryForObject(Queries.SELECT_USER_BY_USERNAME, Map.of("userName", id), new RowMapperUser());
        log.info("Retrieved User: {}", user);
        return user;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

//    public Boolean acceptDonation(Item id);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if(user == null){
            log.error("Repository loadUserByUsername did not find: {}", username);
            throw new UsernameNotFoundException("User not found");
        }else {
            log.info("Repository loadUserByUsername found user: {}", user.getUserName());
            Role role = roleRepository.getRoleByUserName(user.getUserName()); // Correct method
            return new UserPrincipal(user, role);
        }
    }
}
