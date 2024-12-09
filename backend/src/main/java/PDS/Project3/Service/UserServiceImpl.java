package PDS.Project3.Service;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Domain.Entities.Role;
import PDS.Project3.Domain.Entities.User;
import PDS.Project3.Repository.ItemRepository;
import PDS.Project3.Repository.PieceRepository;
import PDS.Project3.Repository.RoleRepository;
import PDS.Project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static PDS.Project3.Domain.RowMapper.RowMapperUser.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRepository;
    private final ItemRepository<Item> itemRepository;
    private final PieceRepository<Piece> pieceRepository;

    @Override
    public UserDTO createUser(User user) {
        return fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUser(String userName) {
        return fromUser(userRepository.getUserByUsername(userName));
    }

    @Override
    public Item getItem(String itemID) {
        return itemRepository.get(itemID);
    }

    @Override
    public List<Piece> getPieces(String itemID) {
        return pieceRepository.findAll(itemID);
    }
}
