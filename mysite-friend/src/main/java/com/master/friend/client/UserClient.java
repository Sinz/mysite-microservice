package com.master.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Classname UserClient
 * @Description TODO
 * @Date 2021/5/12 0:16
 * @Created by Zhao.J
 */
@FeignClient("mysite-user")
public interface UserClient {

    @RequestMapping(value="/user/incfans/{userid}/{x}",method= RequestMethod.POST)
    public void incFanscount(@PathVariable("userid") String userid, @PathVariable("x") int x);

    @RequestMapping(value="/user/incfollow/{userid}/{x}",method= RequestMethod.POST)
    public void incFollowcount(@PathVariable("userid") String userid,@PathVariable("x") int x);
}
