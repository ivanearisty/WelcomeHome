package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Category;
import PDS.Project3.Domain.Entities.Item;

import java.util.List;

public interface CategoryService {
    List<Item> getItems(Category category);
    List<Category> findAll();
}
