package com.mmtzj.service;

import com.google.common.collect.ImmutableMap;
import com.mmtzj.mapper.BaseMapper;
import com.mmtzj.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<E> {

    public List<E> find(Map<String, Object> params) {
        return getMapper().find(params);
    }

    public List<E> findBy(String propertyName, Object value) {
        return getMapper().findBy(propertyName, value);
    }

    public E findByMap(Map<String, Object> param) {
        return getMapper().findByMap(param);
    }

    public List<E> findPage(Map<String, Object> params) {
        return getMapper().find(params);
    }

    public Page<E> findPage(Page<E> page) {
        Number total = getMapper().findPageCount(page.getParam());
        if (total != null && total.longValue() > 0) {
            List<E> result = findPage(toParameterMap(page));
            page.setResult(result);
            page.setTotalItems(total.longValue());
        }
        return page;
    }

    public E get(int id) {
        return getMapper().get(id);
    }

    public abstract BaseMapper<E> getMapper();

    public int saveOrUpdate(E e) {
        return saveOrUpdate(e, null);
    }

    public int saveOrUpdate(E entity, String method) {
        Method m = ReflectionUtils.findMethod(entity.getClass(), method != null ? method : "getId");
        try {
            Object obj = m.invoke(entity, new Object[] {});
            if (obj != null && Integer.parseInt(obj.toString()) > 0) {
                return getMapper().update(entity);
            } else {
                return getMapper().insert(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Map<String, Object> toParameterMap(Page<E> p) {
//        ImmutableMap<String, Object> map = ImmutableMap.of("offset", (Object)p.getOffset(), "limit", p.getPageSize());
        Map<String, Object> map = createQueryParam("offset, limit", p.getOffset(), p.getPageSize());
        if (StringUtils.isNotBlank(p.getOrderBy())) {
            map.put("orderBy", p.getOrderBy());
        }
        if (StringUtils.isNotBlank(p.getOrder())) {
            map.put("order", p.getOrder());
        }
        if (!CollectionUtils.isEmpty(p.getParam())) {
            map.putAll(p.getParam());
        }
        return map;
    }

    public Map<String, Object> createQueryParam(String keys, Object... values) {
        Map<String, Object> param = new HashMap<String, Object>();
        Assert.hasText(keys);
        String[] keySet = keys.split(",");
        int valueIndex = 0;
        for (String key : keySet) {
            param.put(key.trim(), values[valueIndex]);
            valueIndex++;
        }
        return param;
    }

    public List<Map<String, Object>> autoComplete(String term){
        return getMapper().autoComplete(term);
    }
}
