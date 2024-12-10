package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Category;
import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.RowMapper.RowMapperCategory;
import PDS.Project3.Domain.RowMapper.RowMapperItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static PDS.Project3.Queries.Queries.SELECT_ALL_CATEGORIES;
import static PDS.Project3.Queries.Queries.SELECT_ITEMS_BY_CATEGORY;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository<Category> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public List<Category> getAll() {
        try{
            List<Category> categories = jdbc.query(SELECT_ALL_CATEGORIES, new RowMapperCategory());
            log.info("Retrieved categories {}", categories);
            return categories;
        }catch (EmptyResultDataAccessException e){
            log.warn("No categories found");
            throw e;
        } catch (Exception e) {
            log.error("Error getting categories");
            throw e;
        }
    }

    @Override
    public List<Item> getItemsByCategory(Category category) {
        try{
            SqlParameterSource sqlParameterSource = getSQLParameterSource(category);
            List<Item> items = jdbc.query(SELECT_ITEMS_BY_CATEGORY, sqlParameterSource, new RowMapperItem());
            log.info("Retrieved items {}", items);
            return items;
        }catch (EmptyResultDataAccessException e){
            log.warn("No items found for the category {}", category);
            throw e;
        }catch (Exception e){
            log.error("Error getting items for the category {}", category);
            throw e;
        }
    }

    private SqlParameterSource getSQLParameterSource(Category category) {
        return new MapSqlParameterSource()
                .addValue("mainCategory", category.getMainCategory())
                .addValue("subCategory", category.getSubCategory());
    }

}
