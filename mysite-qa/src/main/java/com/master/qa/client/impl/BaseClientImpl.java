package com.master.qa.client.impl;

import com.master.qa.client.BaseClient;
import common.entity.Result;
import common.entity.StatusCode;

/**
 * @Classname BaseClientImpl
 * @Description TODO
 * @Date 2021/5/12 0:26
 * @Created by Zhao.J
 */
public class BaseClientImpl implements BaseClient {

    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR,"熔断器启动了");
    }
}
