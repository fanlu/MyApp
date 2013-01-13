package com.mmtzj.service;

import com.mmtzj.domain.Item;
import com.mmtzj.mapper.BaseMapper;
import com.mmtzj.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-13
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ItemService extends BaseService<Item>{

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public BaseMapper<Item> getMapper() {
        return itemMapper;
    }
}
