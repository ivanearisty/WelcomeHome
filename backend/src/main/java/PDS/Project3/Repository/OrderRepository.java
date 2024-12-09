package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Order;
import PDS.Project3.Domain.Entities.Piece;

import java.util.List;
import java.util.Map;

public interface OrderRepository<T extends Order> {
    public T findById(long id);
    public Map<Item, List<Piece>> findOrderElements(int id);
}
