package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Category;
import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository<Category> categoryRepository;

    @Override
    public List<Item> getItems(Category category) {
        return categoryRepository.getItemsByCategory(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.getAll();
    }
}
