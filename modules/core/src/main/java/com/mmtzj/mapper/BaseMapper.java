package com.mmtzj.mapper;

import com.mmtzj.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 12-10-1
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public interface BaseMapper<E> {

    List<E> find(Map<String, Object> params);

    List<E> findBy(String propertyName, Object value);

    E findByMap(Map<String, Object> param);

    Page<E> findPage(Map<String, Object> params);

    Number findPageCount(Map<String, Object> param);

    E get(int id);

    int insert(E e);

    int remove(int id);

    int update(E e);

    List<Map<String, Object>> autoComplete(String term);

}