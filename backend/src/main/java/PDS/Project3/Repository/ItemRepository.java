package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Location;

public interface ItemRepository<T extends Item> {
    T create(T item);
    T get(String itemId);
    T update(T item);
    Boolean delete(String itemId);
}
