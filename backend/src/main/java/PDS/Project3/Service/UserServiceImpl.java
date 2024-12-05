package PDS.Project3.Service;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.RowMapper.RowMapperUser;
import PDS.Project3.Domain.User;
import PDS.Project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return RowMapperUser.fromUser(userRepository.create(user));
    }
}
