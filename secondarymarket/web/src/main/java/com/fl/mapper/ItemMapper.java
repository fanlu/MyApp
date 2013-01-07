package com.fl.mapper;

import com.fl.domain.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-7
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
@MyBatisRepository
public interface ItemMapper {

    public List<Item> getAll();
}
