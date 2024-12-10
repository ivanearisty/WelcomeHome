package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(String itemId);
    List<Item> getAllItems();
    Item createItem(Item item);
    void deleteItemById(String itemId);
}
