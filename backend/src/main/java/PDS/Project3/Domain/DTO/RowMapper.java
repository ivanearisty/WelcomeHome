package PDS.Project3.Domain.DTO;
import PDS.Project3.Domain.User;
import org.springframework.beans.BeanUtils;

public class RowMapper {
    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    public static User toUser(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
