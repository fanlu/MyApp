package com.mmtzj.service;

import com.mmtzj.domain.Eval;
import com.mmtzj.mapper.BaseMapper;
import com.mmtzj.mapper.EvalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-18
 * Time: 下午10:16
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EvalService extends BaseService<Eval> {

    @Autowired
    private EvalMapper evalMapper;

    @Override
    public BaseMapper<Eval> getMapper() {
        return evalMapper;
    }
}
