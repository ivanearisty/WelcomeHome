package PDS.Project3.Service;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.User;

public interface UserService {
    UserDTO createUser(User user);
}
