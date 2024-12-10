package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Location;
import PDS.Project3.Domain.Entities.Piece;

import java.util.List;

public interface PieceRepository <T extends Piece> {
    List<T> findAll(String itemID);
    Boolean addAll(List<T> pieces);
    T set(T piece);
    T get(String itemId);
    T update(T piece);
    Boolean delete(T piece);
    Location getLocation(int roomNum, int shelfNum);
}
