package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Piece;

import java.util.List;

public interface PieceRepository <T extends Piece> {
    List<T> findAll(String itemID);
    Boolean addAll(List<T> pieces);
    T set(T piece, String itemID);
    T get(String itemId);
    T update(T piece);
    Boolean delete(T piece);
}
