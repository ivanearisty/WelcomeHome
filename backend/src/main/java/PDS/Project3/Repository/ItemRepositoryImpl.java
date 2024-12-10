package PDS.Project3.Repository;

import PDS.Project3.Domain.Entities.Item;
import PDS.Project3.Domain.Entities.Location;
import PDS.Project3.Domain.RowMapper.RowMapperItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;

import static PDS.Project3.Queries.Queries.*;
import static java.util.Map.*;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository<Item> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Item create(Item item) {
        try{
            if(item.getMainCategory() == null || item.getSubCategory() == null){
                log.info("One of the categories had no value, setting to default...");
                item.setMainCategory("Default");
                item.setSubCategory("Default");
            }
            SqlParameterSource parameterSource = getSQLParameterSource(item);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(INSERT_ITEM, parameterSource, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().intValue());
            }
            log.info("Item inserted: {}", item);
            log.info("With parameters: {}", parameterSource.toString());
            return item;
        }catch (EmptyResultDataAccessException exception){
            log.info("Item creation failed for Item: {}", item );
            throw exception;
        }catch (DataIntegrityViolationException exception){
            log.info("Item integrity violation for Item: {}", item );
            throw exception;
        } catch (Exception e){
            e.printStackTrace();
            log.info("Unknown error when creating Item: {}", item);
        }
        return null;
    }

    @Override
    public Item get(String itemId) {
        Item item = jdbc.queryForObject(SELECT_ITEM_BY_ID, of("itemID", itemId) , new RowMapperItem());
        log.info("Retrieved Item: {}", item);
        return item;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public Boolean delete(String itemId) {
        return null;
    }

    private SqlParameterSource getSQLParameterSource(Item item) {
        return new MapSqlParameterSource()
                .addValue("iDescription", item.getDescription())
                .addValue("photo", item.getPhoto())
                .addValue("color", item.getColor())
                .addValue("isNew", item.isNew())
                .addValue("hasPieces", item.isHasPieces())
                .addValue("material", item.getMaterial())
                .addValue("mainCategory", item.getMainCategory())
                .addValue("subCategory", item.getSubCategory());
    }

}
