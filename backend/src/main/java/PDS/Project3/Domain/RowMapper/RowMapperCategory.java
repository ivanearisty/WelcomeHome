package PDS.Project3.Domain.RowMapper;

import PDS.Project3.Domain.Entities.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperCategory implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .mainCategory(rs.getString("mainCategory"))
                .subCategory(rs.getString("subCategory"))
                .catNotes(rs.getString("catNotes"))
                .build();
    }
}
