package com.master.qa.client;

import com.master.qa.client.impl.BaseClientImpl;
import entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Classname LabelClient
 * @Description TODO
 * @Date 2021/5/10 22:36
 * @Created by Zhao.J
 */
@FeignClient(value = "mysite-base",fallback = BaseClientImpl.class)
public interface BaseClient {

    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);
}
