package com.mmtzj.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-25
 * Time: 下午10:04
 * To change this template use File | Settings | File Templates.
 */
public interface AsyncDubboService {
    Map<String, Object> queryForObject(Map<String, Object> param);
}
