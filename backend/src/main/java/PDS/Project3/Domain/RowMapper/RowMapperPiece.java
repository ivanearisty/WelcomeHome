package PDS.Project3.Domain.RowMapper;

import PDS.Project3.Domain.Piece;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperPiece {
    public Piece mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Piece.builder()
                .itemId(rs.getInt("ItemID"))
                .pieceNum(rs.getInt("pieceNum"))
                .description(rs.getString("pDescription"))
                .length(rs.getInt("length"))
                .width(rs.getInt("width"))
                .height(rs.getInt("height"))
                .roomNum(rs.getInt("roomNum"))
                .shelfNum(rs.getInt("shelfNum"))
                .notes(rs.getString("pNotes"))
                .build();
    }
}
