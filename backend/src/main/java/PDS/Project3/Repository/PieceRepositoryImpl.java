package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Domain.RowMapper.RowMapperPiece;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static PDS.Project3.Queries.Queries.SELECT_PIECES_BY_ITEM_ID;

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
    public Boolean addAll(List<Piece> pieces) {
        return null;
    }

    @Override
    public Piece set(Piece piece, String itemID) {
        return null;
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
}
