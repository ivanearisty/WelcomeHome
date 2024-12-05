package PDS.Project3.Repository;

import PDS.Project3.Domain.Role;
import PDS.Project3.Domain.User;
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

import static PDS.Project3.Domain.Roles.ROLE_CLIENT;
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
            user.setEnabled(true);
            user.setNonLocked(true);
            SqlParameterSource parameterSource = getSQLParameterSource(user);
            jdbc.update(INSERT_USER,parameterSource);
            roleRepository.addRoleToUser(ROLE_CLIENT.name(), user.getUserName()); //TODO: Add the addition of the user with custom roles
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
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
