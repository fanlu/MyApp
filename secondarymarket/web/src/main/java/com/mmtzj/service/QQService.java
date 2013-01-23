package com.mmtzj.service;

import com.mmtzj.domain.Category;
import com.mmtzj.domain.Item;
import com.mmtzj.mapper.BaseMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-23
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class QQService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        try{
            return jdbcTemplate.query("SELECT * FROM category", ParameterizedBeanPropertyRowMapper.newInstance(Category.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getAllItems() {
        try{
            return jdbcTemplate.query("select * from item where status=1", ParameterizedBeanPropertyRowMapper.newInstance(Item.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void updateItem(int itemId, String key) {
        String sql = String.format("update item set %s = %s +1 where id=?", key, key);
        jdbcTemplate.update(sql, new Object[]{itemId});
    }
}
