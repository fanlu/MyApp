package com.mmtzj.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
@Service("httpInvokerService")
public class HttpInvokerServiceImpl implements HttpInvokerService {
    @Override
    public void getAll() {

    }

    @Override
    public Map<String, Object> getByMap(String args) {
        Map<String, Object> retMap = Maps.newHashMap();
        retMap.put("args", args);
        return retMap;
    }
}
