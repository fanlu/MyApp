package com.mmtzj.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 下午1:03
 * To change this template use File | Settings | File Templates.
 */
public interface HttpInvokerService {

    public void getAll();

    public Map<String, Object> getByMap(String args);
}
