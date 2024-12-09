package PDS.Project3.Domain.RowMapper;
import PDS.Project3.Domain.DTO.UserDTO;
import PDS.Project3.Domain.Entities.User;
import org.springframework.beans.BeanUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperUser implements org.springframework.jdbc.core.RowMapper<User> {
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

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .userName(rs.getString("userName"))
                .password(rs.getString("password"))
                .firstName(rs.getString("fname"))
                .lastName(rs.getString("lname"))
                .email(rs.getString("email"))
                .nonLocked(rs.getBoolean("is_non_locked"))
                .enabled(rs.getBoolean("is_enabled"))
                .build();
    }
}
