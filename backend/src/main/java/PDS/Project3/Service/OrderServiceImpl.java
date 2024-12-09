package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Order;
import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository<Order> orderRepository;

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order findOrderById(int id) {
        return null;
    }

    @Override
    public Map<Item, List<Piece>> findOrderElements(int id) {
        return orderRepository.findOrderElements(id);
    }
}
