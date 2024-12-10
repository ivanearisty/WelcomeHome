package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Location;
import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Domain.Entities.User;
import PDS.Project3.Domain.RowMapper.RowMapperPiece;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static PDS.Project3.Queries.Queries.*;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PieceRepositoryImpl implements PieceRepository<Piece>{

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public List<Piece> findAll(String itemID) {
        return jdbc.query(SELECT_PIECES_BY_ITEM_ID, Map.of("itemID", itemID), new RowMapperPiece());
    }

    @Override
    public Location getLocation(int roomNum, int shelfNum) {
        try {
            Location location = jdbc.queryForObject(
                    SELECT_LOCATION,
                    Map.of("roomNum", roomNum, "shelfNum", shelfNum),
                    (rs, rowNum) -> new Location(
                            rs.getInt("roomNum"),
                            rs.getInt("shelfNum"),
                            rs.getString("shelf"),
                            rs.getString("shelfDescription")
                    )
            );
            log.info("Retrieved Location: " + location.toString());
            return location;
        } catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }
    @Override
    public Boolean addAll(List<Piece> pieces) {
        return null;
    }

    @Override
    public Piece set(Piece piece) {
        SqlParameterSource parameterSource = getSQLParameterSource(piece);
        jdbc.update(INSERT_PIECE, parameterSource);
        return piece;
    }

    @Override
    public Piece get(String itemId) {
        return null;
    }

    @Override
    public Piece update(Piece piece) {
        return null;
    }

    @Override
    public Boolean delete(Piece piece) {
        return null;
    }

    private SqlParameterSource getSQLParameterSource(Piece piece) {
        return new MapSqlParameterSource()
                .addValue("ItemID", piece.getItemId())
                .addValue("pieceNum", piece.getPieceNum())
                .addValue("pDescription", piece.getDescription())
                .addValue("length", piece.getLength())
                .addValue("width", piece.getWidth())
                .addValue("height", piece.getHeight())
                .addValue("roomNum", piece.getRoomNum())
                .addValue("shelfNum", piece.getShelfNum())
                .addValue("pNotes", piece.getNotes())
                ;
    }
}
