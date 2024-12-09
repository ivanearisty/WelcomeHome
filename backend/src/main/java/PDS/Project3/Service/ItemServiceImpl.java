package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
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
        return null;
    }

    @Override
    public void deleteItemById(String itemId) {

    }
}
