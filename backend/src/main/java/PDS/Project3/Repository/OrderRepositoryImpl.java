package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Order;
import PDS.Project3.Domain.Entities.Piece;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static PDS.Project3.Queries.Queries.SELECT_ORDER_BY_ORDER_ID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository<Order> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public Map<Item, List<Piece>> findOrderElements(int id) {
        Map<Item, List<Piece>> orderElements = new HashMap<>();
        log.info("Find order elements with id: " + id);
        jdbc.query(
                SELECT_ORDER_BY_ORDER_ID,
                Map.of("orderID", id),
                rs -> {
                    Item item = new Item();
                    item.setId(rs.getInt("ItemID"));
                    item.setDescription(rs.getString("ItemDescription"));
                    item.setColor(rs.getString("color"));
                    item.setNew(rs.getBoolean("isNew"));
                    item.setHasPieces(rs.getBoolean("hasPieces"));
                    item.setMaterial(rs.getString("material"));
                    item.setMainCategory(rs.getString("mainCategory"));
                    item.setSubCategory(rs.getString("subCategory"));

                    orderElements.putIfAbsent(item, new ArrayList<>());

                    if (rs.getInt("pieceNum") != 0) {
                        Piece piece = new Piece();
                        piece.setItemId(rs.getInt("ItemID"));
                        piece.setPieceNum(rs.getInt("pieceNum"));
                        piece.setDescription(rs.getString("pDescription"));
                        piece.setLength(rs.getInt("length"));
                        piece.setWidth(rs.getInt("width"));
                        piece.setHeight(rs.getInt("height"));
                        piece.setRoomNum(rs.getInt("roomNum"));
                        piece.setShelfNum(rs.getInt("shelfNum"));
                        piece.setNotes(rs.getString("pNotes"));

                        orderElements.get(item).add(piece);
                    }
                }
        );
        log.info("Order elements found: " + orderElements);
        return orderElements;
    }
}
