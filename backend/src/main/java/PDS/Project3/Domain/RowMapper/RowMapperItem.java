package PDS.Project3.Domain.RowMapper;

import PDS.Project3.Domain.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperItem {
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Item.builder()
                .id(rs.getInt("ItemID"))
                .description(rs.getString("iDescription"))
                .photo(rs.getBytes("photo"))
                .color(rs.getString("color"))
                .isNew(rs.getBoolean("isNew"))
                .hasPieces(rs.getBoolean("hasPieces"))
                .material(rs.getString("material"))
                .mainCategory(rs.getString("mainCategory"))
                .subCategory(rs.getString("subCategory"))
                .build();
    }
}
