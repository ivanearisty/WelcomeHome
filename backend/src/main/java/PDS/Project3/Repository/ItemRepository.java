package PDS.Project3.Repository;

import PDS.Project3.Domain.Item;

import java.util.Collection;

public interface ItemRepository<T extends Item> {
    T create(T role);
    T get(String id);
    T update(T role);
    Boolean delete(String roleId);
}
