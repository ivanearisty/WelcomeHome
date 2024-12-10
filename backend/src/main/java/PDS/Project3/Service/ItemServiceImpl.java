package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository<Item> itemRepository;

    @Override
    public Item getItemById(String itemId) {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return List.of();
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.create(item);
    }

    @Override
    public void deleteItemById(String itemId) {

    }

    @Override
    public boolean orderItem(int itemID, int orderID) {
        return itemRepository.order(itemID, orderID);
    }
}
