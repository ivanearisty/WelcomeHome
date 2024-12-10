package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Category;
import PDS.Project3.Domain.Entities.Item;

import java.util.List;

public interface CategoryRepository<T extends Category> {
    public List<T> getAll();
    public List<Item> getItemsByCategory(T category);
}
