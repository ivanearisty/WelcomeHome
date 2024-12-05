package PDS.Project3.Domain.RowMapper;

import PDS.Project3.Domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperRole implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.builder()
                .roleID(rs.getString("roleID"))
                .roleDescription(rs.getString("roleDescription"))
                .roleDescription(rs.getString("roleDescription"))
                .build();
    }
}
