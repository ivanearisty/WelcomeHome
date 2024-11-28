package PDS.Project3.Repository;

import PDS.Project3.Domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Collection;


public interface UserRepository <T extends User> extends UserDetailsService {
    /* Basic Crud Operations */
    T create(T data);

    Collection<T> list(int page, int pageSize);

    T get(String id);

    T update(T data);

    Boolean delete(String id);
    /* Basic Crud Operations */

    public User getUserByEmail(String email);

}
