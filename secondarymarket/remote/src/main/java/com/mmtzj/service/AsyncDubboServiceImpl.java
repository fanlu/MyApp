package com.mmtzj.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-25
 * Time: 下午10:05
 * To change this template use File | Settings | File Templates.
 */
@Service("asyncDubboService")
public class AsyncDubboServiceImpl implements AsyncDubboService {
    @Override
    public Map<String, Object> queryForObject(Map<String, Object> param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        param.put("async", true);
        return param;
    }
}
