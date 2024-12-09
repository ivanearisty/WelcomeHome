package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Order;
import PDS.Project3.Domain.Entities.Piece;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public Order createOrder(Order order);
    public Order findOrderById(int id);
    public Map<Item, List<Piece>> findOrderElements(int id);
}
