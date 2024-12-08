package PDS.Project3.Service;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.Role;
import PDS.Project3.Domain.User;
import PDS.Project3.Repository.RoleRepository;
import PDS.Project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static PDS.Project3.Domain.RowMapper.RowMapperUser.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRepository;

    @Override
    public UserDTO createUser(User user) {
        return fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUser(String userName) {
        return fromUser(userRepository.getUserByUsername(userName));
    }
}
