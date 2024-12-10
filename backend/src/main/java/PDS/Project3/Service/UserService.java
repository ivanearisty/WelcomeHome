package PDS.Project3.Service;

import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Domain.Entities.User;

import java.util.List;

public interface UserService {
    public UserDTO createUser(User user);
    public UserDTO createUserWithRole(User user, String roleID);

    public UserDTO getUser(String userName);

    public Item getItem(String itemID);

    public List<Piece> getPieces(String itemID);
}
